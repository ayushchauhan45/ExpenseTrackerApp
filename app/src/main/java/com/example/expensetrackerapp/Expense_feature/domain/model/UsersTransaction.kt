package com.example.expensetrackerapp.Expense_feature.domain.model

import androidx.room.Embedded
import androidx.room.Relation


data class UsersTransaction(
 @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userID"
    )
    val transaction: List<Transaction>
)
