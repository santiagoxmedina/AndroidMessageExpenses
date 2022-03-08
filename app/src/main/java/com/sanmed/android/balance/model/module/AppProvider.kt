package com.sanmed.android.balance.model.module

import android.content.Context
import androidx.room.Room
import com.sanmed.android.balance.model.db.AppDataBase
import com.sanmed.android.balance.model.db.CategoryExpenseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppProvider {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room
            .databaseBuilder(context, AppDataBase::class.java, "football-leagues-database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCategoryExpensesDAO(db: AppDataBase): CategoryExpenseDAO {
        return db.categoryExpensesDAO()
    }
}