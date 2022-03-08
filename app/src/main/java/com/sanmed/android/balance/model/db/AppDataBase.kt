package com.sanmed.android.balance.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanmed.android.balance.model.converters.BigDecimalConverter
import com.sanmed.android.balance.model.entities.CategoryExpenseEntity

@Database(entities = [CategoryExpenseEntity::class], version = 1)
@TypeConverters(BigDecimalConverter::class)
abstract class AppDataBase: RoomDatabase() {
    abstract  fun  categoryExpensesDAO(): CategoryExpenseDAO
}