package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetrackerapp.expense_feature.domain.model.User

@Composable
fun UserItem(
    user:User
){
    val backgroundColor = Color(0xFFF8F9FA) // Light gray background

    Card(
       modifier = Modifier
           .fillMaxWidth()
           .height(200.dp),
       shape = RoundedCornerShape(10.dp),
       colors = CardDefaults.cardColors(backgroundColor)
   )  {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            Column() {
                Text(
                    text = "Income",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "$${user.balance}",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Spent",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "$${user.spent}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            }

            Spacer(modifier = Modifier.width(20.dp))
            ExpenseChart(balance = user.balance.toFloat(), spent = user.spent.toFloat())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExpenseChart() {
    val sampleUser = User(
    name = "John Doe",
    balance = 60.0,
    spent = 100.0
    )
    UserItem(user = sampleUser)
}