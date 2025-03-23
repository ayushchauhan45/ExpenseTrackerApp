package com.example.expensetrackerapp.expense_feature.presentation.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun TransactionRadioButtonItem(
    isSelected:Boolean,
    text:String,
    modifier: Modifier = Modifier,
    onClick:()->Unit

){
    Box(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .background(
            color = Color.White,
            shape = RectangleShape
        )
        .border(width = if (isSelected) 2.dp else 0.dp,
            color = if (isSelected)Color(0xFFB6D7A8) else Color.White,
            shape = RoundedCornerShape(16.dp))){
        Row(
            modifier = Modifier.padding( horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = isSelected,
                onClick = {onClick()},
                colors = RadioButtonDefaults.colors(Color(0xFFB6D7A8))
            )
            Text(text = text)
        }

    }

}