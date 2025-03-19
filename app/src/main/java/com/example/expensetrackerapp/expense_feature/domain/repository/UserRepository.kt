package com.example.expensetrackerapp.expense_feature.domain.repository

import androidx.lifecycle.LiveData
import com.example.expensetrackerapp.expense_feature.domain.model.User
import com.example.expensetrackerapp.expense_feature.domain.model.UsersTransaction
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)

    suspend fun getUser(): User

    fun getUsersTransaction(userId: Int): Flow<UsersTransaction>

    suspend fun updateUser(user: User)


}