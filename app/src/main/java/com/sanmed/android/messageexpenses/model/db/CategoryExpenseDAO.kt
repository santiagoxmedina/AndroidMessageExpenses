package com.sanmed.android.messageexpenses.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryExpenseDAO {
    @Insert(onConflict = REPLACE)
    fun save(categoryExpense: CategoryExpenseEntity)

    @Query("SELECT * FROM categoryexpenseentity")
    fun getAll(): Flow<List<CategoryExpenseEntity>>
}