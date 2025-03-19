package com.example.expensetrackerapp.expense_feature.presentation.user.component

import com.example.expensetrackerapp.expense_feature.domain.model.Transaction

data class TransactionState(
    val transaction: List<Transaction> = emptyList()
)
