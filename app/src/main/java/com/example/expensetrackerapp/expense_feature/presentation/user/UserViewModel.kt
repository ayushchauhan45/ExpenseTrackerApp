package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerapp.expense_feature.domain.repository.UserRepository
import com.example.expensetrackerapp.expense_feature.presentation.user.component.TransactionState
import com.example.expensetrackerapp.expense_feature.presentation.user.component.UserState
import com.example.expensetrackerapp.expense_feature.presentation.user.component.nameTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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


    private var currentUserId: Int? = null

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
                      transaction = usersTransaction.transaction
                  )
              }.launchIn(viewModelScope)
        }
    }


}