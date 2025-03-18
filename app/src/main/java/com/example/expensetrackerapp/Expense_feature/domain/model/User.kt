package com.example.expensetrackerapp.Expense_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    val name: String,
    val balance: Double ,
    val spent: Double,
){
    class InvalidUserException(message: String): Exception(message)

}
