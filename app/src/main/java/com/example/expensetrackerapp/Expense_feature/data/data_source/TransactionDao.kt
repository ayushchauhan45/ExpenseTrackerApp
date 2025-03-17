package com.example.expensetrackerapp.Expense_feature.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.expensetrackerapp.Expense_feature.domain.model.User
import com.example.expensetrackerapp.Expense_feature.domain.model.UsersTransaction
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM `transaction` WHERE date >= date('now', 'start of day')")
     fun getDailyTransaction(): LiveData<Flow<List<Transaction>>>

    @Query("SELECT * FROM `transaction` WHERE date >= date('now', '-7 days')")
     fun getWeeklyTransaction(): LiveData<Flow<List<Transaction>>>

    @Query("SELECT * FROM `Transaction` WHERE strftime('%Y-%m',date) >= strftime('%Y-%m', 'now')")
     fun getMonthlyTransaction(): LiveData<Flow<List<Transaction>>>

     @Update
     suspend fun updateTransaction(transaction: Transaction)

}
