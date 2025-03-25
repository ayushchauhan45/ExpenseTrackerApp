package com.example.expensetrackerapp.expense_feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensetrackerapp.expense_feature.domain.model.Transaction
import com.example.expensetrackerapp.expense_feature.domain.model.User


@Database(
    entities = [User::class, Transaction::class],
    version = 1
)
abstract class ExpenseDatabase :RoomDatabase(){

    abstract val userDao:UserDao
    abstract val transactionDao:TransactionDao


    companion object {
        const val DATABASE_NAME = "expenses_db"
    }


}