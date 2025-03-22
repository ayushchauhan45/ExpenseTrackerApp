package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetrackerapp.R
import com.example.expensetrackerapp.expense_feature.domain.model.Transaction

@Composable
fun TransactionItem(
  transaction: Transaction
){

    Row(
        modifier = Modifier.fillMaxWidth().padding(
            start = 10.dp,
            end = 10.dp,
        ).padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
       Box(

       ) {
          Row(
              verticalAlignment = Alignment.CenterVertically
          )  {
               Icon(
                   painter = painterResource(id = getIcon(transaction.category)),
                   contentDescription = null,
                   modifier = Modifier
               )
               Spacer(modifier = Modifier.width(8.dp))

               Column(
                   verticalArrangement = Arrangement.SpaceEvenly
               ) {
                   Text(
                       text = transaction.category,
                       style = MaterialTheme.typography.bodyMedium,
                       fontSize = 16.sp
                   )
                   if (transaction.upi) {
                       Text(
                           text = "UPI",
                           style = MaterialTheme.typography.bodySmall,
                           fontSize = 12.sp
                       )
                   }
                   if (transaction.card) {
                       Text(
                           text = "Card",
                           style = MaterialTheme.typography.bodySmall,
                           fontSize = 12.sp
                       )
                   }
                   if (transaction.cash) {
                       Text(
                           text = "Cash",
                           style = MaterialTheme.typography.bodySmall,
                           fontSize = 12.sp
                       )
                   }
               }
           }
       }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = if (transaction.credit) "+₹${transaction.amount}" else "-₹${transaction.amount}",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp
            )

            Text(
                text = transaction.date,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )

        }
    }
}



fun getIcon(category: String): Int {
    if (category == "Food"){
        return R.drawable.baseline_food_bank_24
    }
    if (category == "Movie"){
        return R.drawable.baseline_movie_24
    }
    if (category == "Water"){
        return R.drawable.baseline_water_drop_24
    }
    if (category == "Taxi"){
        return R.drawable.baseline_local_taxi_24
    }
    if (category == "Rent"){
        return R.drawable.baseline_house_24
    }
    return if (category == "Shopping"){
        R.drawable.baseline_shopping_cart_24
    }
    else R.drawable.baseline_money_24
}