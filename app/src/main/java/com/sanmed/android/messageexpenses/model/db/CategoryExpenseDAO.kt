package com.sanmed.android.messageexpenses.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity
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