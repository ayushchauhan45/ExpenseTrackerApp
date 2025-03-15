package com.example.expensetrackerapp.Expense_feature.domain.model

import androidx.room.PrimaryKey
import java.util.Date

data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    val amount: Double,
    val category: String,
    val date: Date,
    val paymentType: String
)
