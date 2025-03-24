package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerapp.expense_feature.domain.model.Transaction
import com.example.expensetrackerapp.expense_feature.domain.model.User
import com.example.expensetrackerapp.expense_feature.domain.repository.UserRepository
import com.example.expensetrackerapp.expense_feature.presentation.user.component.TransactionState
import com.example.expensetrackerapp.expense_feature.presentation.user.component.UserState
import com.example.expensetrackerapp.expense_feature.presentation.user.component.nameTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle:SavedStateHandle
):ViewModel(){
    private val _nameTextFieldState = mutableStateOf(nameTextFieldState())
    val nameTextFieldState:State<nameTextFieldState> = _nameTextFieldState

    private val _userState = mutableStateOf(UserState())
    val userState:State<UserState> = _userState

    private val _transactionState = mutableStateOf(TransactionState())
    val transactionState:State<TransactionState> = _transactionState

    private var getTransactionJob: Job? = null


    init {
        savedStateHandle.get<Int>("userId")?.let { userId ->
            getTransactionJob?.cancel()
              getTransactionJob =  userRepository.getUsersTransaction(userId).onEach {usersTransaction ->
                  _userState.value = _userState.value.copy(
                      name  = usersTransaction.user.name,
                      balance = usersTransaction.user.balance,
                      spent = usersTransaction.user.spent
                  )
                  _transactionState.value = _transactionState.value.copy(
                      transaction = usersTransaction.transaction.sortedByDescending { it.transactionId }
                  )
              }.launchIn(viewModelScope)
        }
    }


    fun addUser(){
        viewModelScope.launch {
            userRepository.insertUser(
                User(
                    name  = nameTextFieldState.value.text,
                    balance = 0.0,
                    spent = 0.0
                )
            )
        }
    }

    fun updateUserWhenCredit(name:String, amount :Double, balance:Double,spent:Double){
        viewModelScope.launch {
            userRepository.updateUser(
                User(
                    name = name  ,
                    balance = balance + amount,
                    spent = spent
                )
            )
        }
    }
    fun updateUserWhenDebit(name:String, amount :Double, balance:Double,spent:Double){
        viewModelScope.launch {
            userRepository.updateUser(
                User(
                    name = name  ,
                    balance = balance - amount,
                    spent = spent + amount
                )
            )
        }
    }



}