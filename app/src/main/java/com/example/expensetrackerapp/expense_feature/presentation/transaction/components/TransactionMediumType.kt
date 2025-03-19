package com.example.expensetrackerapp.expense_feature.presentation.transaction.components

data class TransactionMediumType(
    val cash: Boolean = false,
    val card:Boolean = false,
    val upi:Boolean = false
)