package com.example.expensetrackerapp.Expense_feature.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.expensetrackerapp.Expense_feature.domain.model.User
import com.example.expensetrackerapp.Expense_feature.domain.model.UsersTransaction
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<User>

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUsersTransaction(userId:Int): UsersTransaction
}
