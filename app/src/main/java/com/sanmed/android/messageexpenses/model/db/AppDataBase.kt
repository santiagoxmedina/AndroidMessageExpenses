package com.sanmed.android.messageexpenses.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanmed.android.messageexpenses.model.converters.BigDecimalConverter
import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity

@Database(entities = [CategoryExpenseEntity::class], version = 1, exportSchema = false)
@TypeConverters(BigDecimalConverter::class)
abstract class AppDataBase: RoomDatabase() {
    abstract  fun  categoryExpensesDAO(): CategoryExpenseDAO
}