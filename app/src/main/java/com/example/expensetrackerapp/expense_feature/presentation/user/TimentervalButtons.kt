package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimeIntervalButtons(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text:String,
    isSelected: Boolean
){
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier,
        border = BorderStroke(1.dp, if (isSelected) Color(0xFF90EE90) else Color.White)
    ) {
        Text(text = text)
    }

}