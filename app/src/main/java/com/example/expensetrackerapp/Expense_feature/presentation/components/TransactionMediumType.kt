package com.example.expensetrackerapp.Expense_feature.presentation.components

data class TransactionMediumType(
    val cash: Boolean = false,
    val card:Boolean = false,
    val upi:Boolean = false
)