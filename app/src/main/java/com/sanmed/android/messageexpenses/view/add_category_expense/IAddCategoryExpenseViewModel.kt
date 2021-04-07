package com.sanmed.android.messageexpenses.view.add_category_expense

import androidx.lifecycle.LiveData

interface IAddCategoryExpenseViewModel:IAddCategoryExpenseView {

    fun getOnDisMiss():LiveData<Boolean>
    fun getOnDisMissCompleted()
}