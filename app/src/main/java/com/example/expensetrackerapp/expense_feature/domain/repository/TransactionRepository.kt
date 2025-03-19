package com.example.expensetrackerapp.expense_feature.domain.repository

import com.example.expensetrackerapp.expense_feature.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun insertTransaction(transaction: Transaction)

    fun getDailyTransaction(): Flow<List<Transaction>>

    fun getWeeklyTransaction(): Flow<List<Transaction>>

    fun getMonthlyTransaction(): Flow<List<Transaction>>

    suspend fun updateTransaction(transaction: Transaction)

}