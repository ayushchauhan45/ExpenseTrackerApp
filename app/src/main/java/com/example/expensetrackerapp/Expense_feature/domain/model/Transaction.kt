package com.example.expensetrackerapp.Expense_feature.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Int?= null,
    val amount: Double,
    val category: String,
    val type: String,
    val date: Date,
    val paymentType: String
)
