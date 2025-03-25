package com.example.expensetrackerapp.expense_feature.presentation.constant

sealed class Screens(val route: String) {
    data object WelcomeScreen: Screens("welcome_screen")
    data object UserScreen: Screens("user_screen")
    data object AddTransactionScreen: Screens("transaction_screen")
    data object AllTransactionsScreen:Screens("all_transaction_screen")
}