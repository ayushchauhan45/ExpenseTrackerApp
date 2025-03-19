package com.example.expensetrackerapp.expense_feature.presentation.transaction.components

sealed class TransactionEvents {
    data class OnAmountValueChange(val value: String): TransactionEvents()
    data class OnCategoryValueChange(val value: String): TransactionEvents()
    data object SaveTransaction: TransactionEvents()
    data class OnExpenseTypeChange(val expenseTypeState: ExpenseTypeState): TransactionEvents()
    data class OnTransactionMediumTypeChange(val transactionMediumTypeState: TransactionMediumType): TransactionEvents()

}