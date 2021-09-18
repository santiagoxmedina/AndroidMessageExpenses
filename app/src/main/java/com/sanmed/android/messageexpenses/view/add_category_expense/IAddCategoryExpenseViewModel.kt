package com.sanmed.android.messageexpenses.view.add_category_expense

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.categories_expenses.ICategoryExpenseView

interface IAddCategoryExpenseViewModel:IAddCategoryExpenseView {

    fun getOnDisMiss():LiveData<Boolean>
    fun getOnDisMissCompleted()
    fun onEditCategoryExpense(categoryExpenseView: ICategoryExpenseView)
    fun onAddCategoryExpense()
    fun onAddCategoryExpenseCompleted()
    fun getShowDialog(): LiveData<Boolean>
}