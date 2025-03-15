package com.example.expensetrackerapp.Expense_feature.domain.model

import androidx.room.PrimaryKey
import java.util.Date

data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    val name: String,
    val income: Double,
    val spent: Double,
    val saving: Double
)
