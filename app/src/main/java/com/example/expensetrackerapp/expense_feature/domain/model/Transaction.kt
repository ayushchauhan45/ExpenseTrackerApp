package com.example.expensetrackerapp.expense_feature.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userID"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userID"])]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Int?= null,
    val userID: Int,
    val amount: Double,
    val category: String,
    val date: String,
    val debit:Boolean,
    val credit:Boolean,
    val cash: Boolean,
    val card:Boolean,
    val upi:Boolean
){
    class InvalidTransactionException(message: String): Exception(message)

}
