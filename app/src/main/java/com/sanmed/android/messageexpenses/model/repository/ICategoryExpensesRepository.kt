package com.sanmed.android.messageexpenses.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView

interface ICategoryExpensesRepository {
    suspend fun addCategory(categoryExpenseView: ICategoryExpenseView)
    fun getCategoriesExpenses(): LiveData<List<ICategoryExpenseView?>>
    suspend fun delete(categoryExpenseView: ICategoryExpenseView)
    suspend fun editCategory(categoryExpenseView: ICategoryExpenseView)
}