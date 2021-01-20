package com.sanmed.android.messageexpenses.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanmed.android.messageexpenses.model.entities.Expense

@Database(entities = [Expense::class], version = 1)
abstract class ExpenseDataBase: RoomDatabase() {
    abstract  fun  expensesDAO(): ExpensesDAO
}