package com.sanmed.android.balance.model.db

import androidx.room.*
import com.sanmed.android.balance.model.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(expense: ExpenseEntity)

    @Query("SELECT * FROM expenseentity")
    fun getAll(): Flow<List<ExpenseEntity>>

    @Delete
    fun delete(expense: ExpenseEntity)

    @Update
    fun update(expense: ExpenseEntity)
}