package com.sanmed.android.messageexpenses.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.model.data_source.ILocalCategoryExpensesDataSource
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import javax.inject.Inject

class CategoryExpensesRepository @Inject constructor(private val categoryExpensesDataSource: ILocalCategoryExpensesDataSource):ICategoryExpensesRepository  {
    override suspend fun addCategory(categoryExpenseView: ICategoryExpenseView) {
        categoryExpensesDataSource.addCategory(categoryExpenseView)
    }

    override fun getCategoriesExpenses(): LiveData<List<ICategoryExpenseView?>> {
        return categoryExpensesDataSource.getCategoriesExpenses()
    }
}