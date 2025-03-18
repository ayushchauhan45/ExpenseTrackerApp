package com.example.expensetrackerapp.Expense_feature.domain.repository

import androidx.lifecycle.LiveData
import com.example.expensetrackerapp.Expense_feature.domain.model.User
import com.example.expensetrackerapp.Expense_feature.domain.model.UsersTransaction

interface UserRepository {
    suspend fun insertUser(user: User)

    suspend fun getUser(): User

    suspend fun getUsersTransaction(userId: Int): LiveData<UsersTransaction>

    suspend fun updateUser(user: User)


}