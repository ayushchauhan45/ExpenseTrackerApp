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
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetrackerapp.expense_feature.presentation.transaction.components.TransactionEvents

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransactionScreen(
) {

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
                top = 16.dp,
                start = 12.dp,
                end = 12.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .padding(18.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = Color.White,
                    shape = RectangleShape
                )
                .border(width = 2.dp, color = Color(0xFFEAEAEA), shape = RoundedCornerShape(16.dp))){
            IconButton(
                    onClick = {}
            ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = "Add Transaction",
            modifier = Modifier.padding(14.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.W400
        )
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {
            },
            placeholder = {
                Text(text = "Amount")
            },
            enabled = true,
            modifier = Modifier.background(
                Color(0xFFF5F5F5)
            ).fillMaxWidth(fraction = 0.82f).clip(RoundedCornerShape(12.dp)).padding(12.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {
            },
            placeholder = {
                Text(text = "Category")
            },
            enabled = true,
            modifier = Modifier.background(
                Color(0xFFF5F5F5)
            ).fillMaxWidth(fraction = 0.82f).clip(RoundedCornerShape(12.dp)).padding(16.dp),
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewAddTransaction(){
        AddTransactionScreen()
}
