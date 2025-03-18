package com.example.expensetrackerapp.Expense_feature.data.repository

import com.example.expensetrackerapp.Expense_feature.data.data_source.TransactionDao
import com.example.expensetrackerapp.Expense_feature.data.data_source.UserDao
import com.example.expensetrackerapp.Expense_feature.domain.model.Transaction
import com.example.expensetrackerapp.Expense_feature.domain.model.User
import com.example.expensetrackerapp.Expense_feature.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
): TransactionRepository {
    override suspend fun insertTransaction(transaction: Transaction) {
        if (transaction.category.isBlank()){
            throw Transaction.InvalidTransactionException("The category of the transaction can't be empty")
        }
        transactionDao.insertTransaction(transaction)
    }

    override fun getDailyTransaction(): Flow<List<Transaction>> {
        return flow{
            transactionDao.getDailyTransaction()
        }
    }

    override fun getWeeklyTransaction(): Flow<List<Transaction>> {
        return flow {
            transactionDao.getWeeklyTransaction()
        }
    }

    override fun getMonthlyTransaction(): Flow<List<Transaction>> {
        return flow {
            transactionDao.getMonthlyTransaction()
        }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }
}