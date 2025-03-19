package com.example.expensetrackerapp.expense_feature.presentation.user.component

import com.example.expensetrackerapp.expense_feature.domain.model.Transaction

data class UserState(
    val name: String = "",
    val balance: Double = 0.0,
    val spent: Double = 0.0
)
