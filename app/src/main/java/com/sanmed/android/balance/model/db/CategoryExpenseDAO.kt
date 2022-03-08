package com.sanmed.android.balance.model.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.sanmed.android.balance.model.entities.CategoryExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryExpenseDAO {
    @Insert(onConflict = REPLACE)
    fun save(categoryExpense: CategoryExpenseEntity)

    @Query("SELECT * FROM categoryexpenseentity")
    fun getAll(): Flow<List<CategoryExpenseEntity>>

    @Delete
    fun delete(categoryExpense: CategoryExpenseEntity)

    @Update
    fun update(categoryExpense: CategoryExpenseEntity)
}