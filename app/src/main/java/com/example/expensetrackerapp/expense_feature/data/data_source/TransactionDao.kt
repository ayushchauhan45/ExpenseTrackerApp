package com.example.expensetrackerapp.expense_feature.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetrackerapp.expense_feature.domain.model.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM `transaction` WHERE date >= date('now', 'start of day')")
     fun getDailyTransaction(): Flow<List<Transaction>>

    @Query("SELECT * FROM `transaction` WHERE date >= date('now', '-7 days')")
     fun getWeeklyTransaction(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE strftime('%Y-%m',date) >= strftime('%Y-%m', 'now')")
     fun getMonthlyTransaction(): Flow<List<Transaction>>

     @Update
     suspend fun updateTransaction(transaction: Transaction)

}
