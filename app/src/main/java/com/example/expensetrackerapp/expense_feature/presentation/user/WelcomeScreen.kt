package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.TransactionEvents
import com.example.expensetrackerapp.expense_feature.presentation.user.component.UsersEvents
import kotlinx.coroutines.flow.collectLatest


@Composable
fun WelcomeScreen(
    userViewModel: UserViewModel = hiltViewModel()
){
    val userState = userViewModel.nameTextFieldState

    LaunchedEffect(key1 = true) {
        userViewModel.eventFlow.collectLatest {event->
            when(event){
                UserViewModel.UserUiEvent.SaveUser -> {

            }
         }
       }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(
            top = 20.dp,
            start = 20.dp,
            end = 20.dp
        ),
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Hello,",
            fontSize = 50.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Welcome",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            value = userState.value.text,
            onValueChange = {
                userViewModel.onEvent(UsersEvents.OnNameValueChange(it))
            },
            placeholder = {
               Text(text = "Enter your name....")
            },
            enabled = true,
            modifier = Modifier
                .background(
                    Color(0xFFF5F5F5)
                )
                .fillMaxWidth(fraction = .9f)
                .clip(RoundedCornerShape(12.dp))
                .padding(start = 21.dp),
        )


        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                userViewModel.onEvent(UsersEvents.SaveTransaction)
            }
        ) {
            Text(text = "Enter")
        }


    }


}