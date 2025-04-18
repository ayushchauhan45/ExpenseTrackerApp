package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expensetrackerapp.expense_feature.domain.model.User
import com.example.expensetrackerapp.expense_feature.presentation.transaction.TransactionViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    navController: NavController
){
   val userState = userViewModel.userState
   val transactionState = userViewModel.transactionState
   var isSelected by remember { mutableStateOf("All") }


    LaunchedEffect(key1 = true) {
        transactionViewModel.eventFlow.collectLatest {event->
            when(event){
                TransactionViewModel.UiEvent.SaveTransaction -> {
                    if (transactionState.value.transaction[0].credit)
                        userViewModel.updateUserWhenCredit(
                            name = userState.value.name,
                            amount = transactionState.value.transaction[0].amount ,
                            balance = userState.value.balance,
                            spent = userState.value.spent
                        )
                    if (transactionState.value.transaction[0].debit)
                    userViewModel.updateUserWhenDebit(
                        name = userState.value.name,
                        amount = transactionState.value.transaction[0].amount ,
                        balance = userState.value.balance,
                        spent = userState.value.spent
                    )
                }
            }
        }
    }

    Column(
        modifier =  Modifier.fillMaxSize()
    ){
        Text(text = "Hello,",
            fontSize = 50.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = userState.value.name,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeIntervalButtons(
                onClick = {
                    isSelected = "All"
                },
                text = "All",
                isSelected = isSelected == "All"
            )
            TimeIntervalButtons(
                onClick = {
                    isSelected = "Daily"
                    transactionViewModel.getDailyTransaction()
                },
                text = "Daily",
                isSelected = isSelected == "Daily"
            )
            TimeIntervalButtons(
                onClick = {
                    isSelected = "Weekly"
                    transactionViewModel.getWeeklyTransaction()
                },
                text = "Weekly",
                isSelected = isSelected == "Weekly"
            )
            TimeIntervalButtons(
                onClick = {
                    isSelected = "Monthly"
                    transactionViewModel.getMonthlyTransaction()
                },
                text = "Monthly",
                isSelected = isSelected == "Monthly"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (isSelected == "All") {
            UserItem(
                user = User(
                    name = userState.value.name,
                    balance = userState.value.balance,
                    spent = userState.value.spent
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(transactionState.value.transaction.take(4)) { transaction ->
                    TransactionItem(transaction)
                }
            }
        }
        if(isSelected == "Daily" ){
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(transactionViewModel.dailyTransactions.value){transaction->
                    TransactionItem(transaction)
                }
            }
        }
        if(isSelected == "Weekly" ){
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(transactionViewModel.weeklyTransactions.value){transaction->
                    TransactionItem(transaction)
                }
            }
        }
        if(isSelected == "Daily" ){
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(transactionViewModel.monthlyTransactions.value){transaction->
                    TransactionItem(transaction)
                }
            }
        }

    }
}


