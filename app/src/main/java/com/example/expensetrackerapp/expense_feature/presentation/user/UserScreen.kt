package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerapp.expense_feature.presentation.transaction.TransactionViewModel

@Composable
fun UserScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel = hiltViewModel()
){
   val userState = userViewModel.userState.value
   val transactionState = userViewModel.transactionState






}