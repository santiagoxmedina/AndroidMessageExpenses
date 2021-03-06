package com.sanmed.android.messageexpenses.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity

@Database(entities = [CategoryExpenseEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract  fun  categoryExpensesDAO(): CategoryExpenseDAO
}