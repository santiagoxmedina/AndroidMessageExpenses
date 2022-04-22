package com.sanmed.android.balance.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanmed.android.balance.model.converters.BigDecimalConverter
import com.sanmed.android.balance.model.converters.CalendarConverter
import com.sanmed.android.balance.model.entities.CategoryExpenseEntity
import com.sanmed.android.balance.model.entities.ExpenseEntity

@Database(entities = [CategoryExpenseEntity::class, ExpenseEntity::class], version = 2)
@TypeConverters(BigDecimalConverter::class,CalendarConverter::class)
abstract class AppDataBase: RoomDatabase() {
    abstract  fun  categoryExpensesDAO(): CategoryExpenseDAO
    abstract  fun  expenseDAO(): ExpenseDAO
}