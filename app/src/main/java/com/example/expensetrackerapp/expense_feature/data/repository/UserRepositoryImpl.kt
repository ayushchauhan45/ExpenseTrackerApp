package com.example.expensetrackerapp.expense_feature.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.expensetrackerapp.expense_feature.data.data_source.UserDao
import com.example.expensetrackerapp.expense_feature.domain.model.User
import com.example.expensetrackerapp.expense_feature.domain.model.UsersTransaction
import com.example.expensetrackerapp.expense_feature.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
): UserRepository {
    override suspend fun insertUser(user: User) {
        val userId = userDao.insertUser(user)
       sharedPreferences.edit().putInt("user_id", userId.toInt()).apply()
    }

    override suspend fun getUser(): User {
       return userDao.getUser()
    }

    override fun getUsersTransaction(userId: Int): Flow<UsersTransaction> {
        return userDao.getUsersTransaction(userId)
    }

    override suspend fun updateUser(user: User) {
         userDao.updateUser(user)
    }
}