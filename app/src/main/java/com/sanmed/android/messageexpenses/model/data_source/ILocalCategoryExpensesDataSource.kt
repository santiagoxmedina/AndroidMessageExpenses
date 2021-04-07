package com.sanmed.android.messageexpenses.model.data_source

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView

interface ILocalCategoryExpensesDataSource {
    fun addCategory(categoryExpenseView: ICategoryExpenseView)
    fun getCategoriesExpenses(): LiveData<List<ICategoryExpenseView?>>
}