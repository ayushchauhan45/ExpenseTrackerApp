package com.example.expensetrackerapp.Expense_feature.data.repository

import androidx.lifecycle.LiveData
import com.example.expensetrackerapp.Expense_feature.data.data_source.UserDao
import com.example.expensetrackerapp.Expense_feature.domain.model.User
import com.example.expensetrackerapp.Expense_feature.domain.model.UsersTransaction
import com.example.expensetrackerapp.Expense_feature.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUser(): User {
       return userDao.getUser()
    }

    override suspend fun getUsersTransaction(userId: Int): LiveData<UsersTransaction> {
        return userDao.getUsersTransaction(userId)
    }

    override suspend fun updateUser(user: User) {
         userDao.updateUser(user)
    }
}