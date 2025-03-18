package com.example.expensetrackerapp.Expense_feature.domain.repository

import com.example.expensetrackerapp.Expense_feature.domain.model.Transaction
import com.example.expensetrackerapp.Expense_feature.domain.model.User
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun insertTransaction(transaction: Transaction, user: User)

    fun getDailyTransaction(): Flow<List<Transaction>>

    fun getWeeklyTransaction(): Flow<List<Transaction>>

    fun getMonthlyTransaction(): Flow<List<Transaction>>

    suspend fun updateTransaction(transaction: Transaction)

}