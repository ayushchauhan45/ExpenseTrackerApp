package com.example.expensetrackerapp.expense_feature.domain.usescases.UserUseCase

import com.example.expensetrackerapp.expense_feature.domain.model.User
import com.example.expensetrackerapp.expense_feature.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    @Throws(User.InvalidUserException::class)
    suspend operator fun invoke(user: User){
        if (user.name.isBlank()){
            throw User.InvalidUserException("The name of the user can't be empty")
        }
        userRepository.insertUser(user)
    }
}