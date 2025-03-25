package com.example.expensetrackerapp.expense_feature.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.expensetrackerapp.expense_feature.data.data_source.ExpenseDatabase
import com.example.expensetrackerapp.expense_feature.data.repository.TransactionRepositoryImpl
import com.example.expensetrackerapp.expense_feature.data.repository.UserRepositoryImpl
import com.example.expensetrackerapp.expense_feature.domain.repository.TransactionRepository
import com.example.expensetrackerapp.expense_feature.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application):ExpenseDatabase{
        return Room.databaseBuilder(
            app,
            ExpenseDatabase::class.java,
            ExpenseDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db:ExpenseDatabase): TransactionRepository {
        return TransactionRepositoryImpl(db.transactionDao)
    }

    @Provides
    @Singleton
    fun providesUserRepository(db:ExpenseDatabase,prefs: SharedPreferences): UserRepository {
        return UserRepositoryImpl(db.userDao,prefs)
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(app:Application):SharedPreferences{
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

}