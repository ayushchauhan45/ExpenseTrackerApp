package com.example.expensetrackerapp.expense_feature.presentation.user.component

sealed class UsersEvents {
    data class OnNameValueChange(val value: String): UsersEvents()
    data object SaveTransaction: UsersEvents()
}