package com.sanmed.android.messageexpenses.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sanmed.android.messageexpenses.entities.Expense

@Dao
interface ExpensesDAO {
    @Insert(onConflict = REPLACE)
    fun save(expenses: List<Expense>)

    @Query("SELECT * FROM expense")
    fun load():LiveData<List<Expense>>
}