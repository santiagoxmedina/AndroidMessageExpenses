package com.sanmed.android.balance.model.data_source

import androidx.lifecycle.LiveData
import com.sanmed.android.balance.view.categories_expenses.ICategoryExpenseView

interface ILocalCategoryExpensesDataSource {
    fun addCategory(categoryExpenseView: ICategoryExpenseView)
    fun getCategoriesExpenses(): LiveData<List<ICategoryExpenseView?>>
    fun delete(categoryExpenseView: ICategoryExpenseView)
    fun editCategory(categoryExpenseView: ICategoryExpenseView)
}