package com.example.expensetrackerapp.expense_feature.presentation.transaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.TransactionEvents
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransactionScreen(
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    val amountTextField = transactionViewModel.expenseAmountText.value
    val categoryTextField = transactionViewModel.expenseCategoryText.value
    val transactionMethod = transactionViewModel.transactionMediumType.value
    val transactionType = transactionViewModel.expenseType.value


   Scaffold(
       bottomBar = {
           Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               contentAlignment = Alignment.Center
           ) {
               Button(
                   onClick = {
                       transactionViewModel.onEvent(TransactionEvents.SaveTransaction)
                   },
                   modifier = Modifier.fillMaxWidth(0.9f),
                   colors = ButtonDefaults.buttonColors(Color(0xFFA4C8A0))
               ) {
                   Text(text = "Add")
               }
           }
       }
   ){padding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp
                    )
                )
                .padding(
                    padding
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(18.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            color = Color.White,
                            shape = RectangleShape
                        )
                        .border(
                            width = 2.dp,
                            color = Color(0xFFEAEAEA),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Add Transaction",
                    modifier = Modifier.padding(14.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = amountTextField.amount,
                onValueChange = {
                    transactionViewModel.onEvent(TransactionEvents.OnAmountValueChange(it))
                },
                placeholder = {
                    Text(text = "Amount")
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
            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = categoryTextField.text,
                onValueChange = {
                    transactionViewModel.onEvent(TransactionEvents.OnCategoryValueChange(it))
                },
                placeholder = {
                    Text(text = "Category")
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
            Text(
                text = "Payment Type",
                modifier = Modifier.padding(
                    start = 16.dp
                ),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(16.dp))

            TransactionRadioButtonItem(
                isSelected = transactionType.credit,
                onClick = {
                    transactionType.credit = true
                    transactionType.debit = false
                },
                text = "Credit",
            )
            Spacer(modifier = Modifier.height(4.dp))
            TransactionRadioButtonItem(
                isSelected = transactionType.debit,
                onClick = {
                    transactionType.debit = true
                    transactionType.credit = false

                },
                text = "Debit",
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Payment Method",
                modifier = Modifier.padding(
                    start = 16.dp
                ),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(16.dp))

            TransactionRadioButtonItem(
                isSelected = transactionMethod.cash,
                onClick = {
                    transactionMethod.cash = true
                    transactionMethod.card = false
                    transactionMethod.upi = false
                },
                text = "Cash",
            )
            Spacer(modifier = Modifier.height(4.dp))
            TransactionRadioButtonItem(
                isSelected = transactionMethod.upi,
                onClick = {
                    transactionMethod.upi = true
                    transactionMethod.cash = false
                    transactionMethod.card = false
                },
                text = "UPI",
            )
            Spacer(modifier = Modifier.height(4.dp))
            TransactionRadioButtonItem(
                isSelected = transactionMethod.card,
                onClick = {
                    transactionMethod.card = true
                    transactionMethod.cash = false
                    transactionMethod.upi = false
                },
                text = "Card",
            )
        }
    }
}


