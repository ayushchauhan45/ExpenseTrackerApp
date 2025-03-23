package com.example.expensetrackerapp.expense_feature.presentation.transaction.components

data class TransactionMediumType(
    var cash: Boolean = false,
    var card:Boolean = false,
    var upi:Boolean = false
)