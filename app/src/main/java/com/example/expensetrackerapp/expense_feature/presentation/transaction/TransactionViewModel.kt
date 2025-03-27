package com.example.expensetrackerapp.expense_feature.presentation.transaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerapp.expense_feature.domain.model.Transaction
import com.example.expensetrackerapp.expense_feature.domain.repository.TransactionRepository
import com.example.expensetrackerapp.expense_feature.domain.repository.UserRepository
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.ExpenseAmountTextFieldState
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.ExpenseCategoryTextFieldState
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.ExpenseTypeState
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.TransactionEvents
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.TransactionMediumType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
 private val transactionRepository: TransactionRepository,
 private val userRepository: UserRepository
):ViewModel() {


    private val _expenseAmountTextState = mutableStateOf(ExpenseAmountTextFieldState())
    val expenseAmountText:State<ExpenseAmountTextFieldState> = _expenseAmountTextState

    private val _expenseCategoryTextState = mutableStateOf(ExpenseCategoryTextFieldState())
    val expenseCategoryText:State<ExpenseCategoryTextFieldState> = _expenseCategoryTextState

    private val _expenseTypeState = mutableStateOf(ExpenseTypeState())
    val expenseType:State<ExpenseTypeState> = _expenseTypeState

    private val _transactionMediumTypeState = mutableStateOf(TransactionMediumType())
    val transactionMediumType:State<TransactionMediumType> = _transactionMediumTypeState

    private val _eventFlow =  MutableSharedFlow<UiEvent>()
    val eventFlow =  _eventFlow.asSharedFlow()

    val dailyTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    val weeklyTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    val monthlyTransactions = MutableStateFlow<List<Transaction>>(emptyList())



    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: TransactionEvents){
        when(event){
            is TransactionEvents.OnExpenseTypeChange -> {
                _expenseTypeState.value = _expenseTypeState.value.copy(
                    credit = event.expenseTypeState.credit,
                    debit = event.expenseTypeState.debit
                )
            }
            is TransactionEvents.OnTransactionMediumTypeChange -> {
                _transactionMediumTypeState.value = _transactionMediumTypeState.value.copy(
                    cash = !event.transactionMediumTypeState.cash,
                    card = !event.transactionMediumTypeState.card,
                    upi = !event.transactionMediumTypeState.upi
                )
            }

            is TransactionEvents.OnAmountValueChange -> {
                _expenseAmountTextState.value = _expenseAmountTextState.value.copy(
                    amount = event.value
                )
            }

            TransactionEvents.SaveTransaction -> {
                viewModelScope.launch {
                    try {
                        userRepository.getUser().id?.let {
                            Transaction(
                                amount = expenseAmountText.value.amount.toDoubleOrNull() ?: 0.0,
                                category = expenseCategoryText.value.text ,
                                date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd", Locale.getDefault())),
                                debit = expenseType.value.debit,
                                credit = expenseType.value.credit,
                                cash = transactionMediumType.value.cash,
                                card = transactionMediumType.value.card,
                                upi = transactionMediumType.value.upi,
                                userID = it
                            )
                        }?.let {
                            transactionRepository.insertTransaction(
                                it
                            )
                        }
                        _eventFlow.emit(UiEvent.SaveTransaction)
                    }catch (e: Transaction.InvalidTransactionException){
                      e.message
                    }
                }
            }
           is TransactionEvents.OnCategoryValueChange ->{
                _expenseCategoryTextState.value = _expenseCategoryTextState.value.copy(
                    text = event.value
                )
            }
        }
    }

    fun getDailyTransaction(){
        viewModelScope.launch {
           transactionRepository.getDailyTransaction().collect{transaction->
               dailyTransactions.value = transaction
           }
        }
    }
    fun getWeeklyTransaction(){
        viewModelScope.launch {
            transactionRepository.getWeeklyTransaction().collect{transaction->
                weeklyTransactions.value = transaction
            }
        }
    }
    fun getMonthlyTransaction(){
        viewModelScope.launch {
            transactionRepository.getMonthlyTransaction().collect{transaction->
                monthlyTransactions.value = transaction
            }
        }
    }


    sealed interface UiEvent{
        data object SaveTransaction: UiEvent
    }



}