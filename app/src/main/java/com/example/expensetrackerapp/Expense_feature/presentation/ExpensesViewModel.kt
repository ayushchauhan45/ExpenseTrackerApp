package com.example.expensetrackerapp.Expense_feature.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.expensetrackerapp.Expense_feature.domain.repository.TransactionRepository
import com.example.expensetrackerapp.Expense_feature.domain.repository.UserRepository
import com.example.expensetrackerapp.Expense_feature.presentation.components.ExpenseTextFieldState
import com.example.expensetrackerapp.Expense_feature.presentation.components.ExpenseTypeState
import com.example.expensetrackerapp.Expense_feature.presentation.components.TransactionEvents
import com.example.expensetrackerapp.Expense_feature.presentation.components.TransactionMediumType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ExpensesViewModel @Inject constructor(
 private val transactionRepository: TransactionRepository,
 private val userRepository: UserRepository
):ViewModel() {


    private val _expenseTextState = mutableStateOf(ExpenseTextFieldState())
    val expenseText:State<ExpenseTextFieldState> = _expenseTextState

    private val _expenseTypeState = mutableStateOf(ExpenseTypeState())
    val expenseType:State<ExpenseTypeState> = _expenseTypeState

    private val _transactionMediumTypeState = mutableStateOf(TransactionMediumType())
    val transactionMediumType:State<TransactionMediumType> = _transactionMediumTypeState

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
            is TransactionEvents.OnValueChange -> {
                _expenseTextState.value = _expenseTextState.value.copy(
                    value = event.value
                )

            }
        }


    }


    sealed interface UiEvent{
        data object SaveTransaction:UiEvent
    }



}