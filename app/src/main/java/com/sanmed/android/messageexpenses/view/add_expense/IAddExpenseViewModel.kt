package com.sanmed.android.messageexpenses.view.add_expense

import androidx.lifecycle.LiveData

interface IAddExpenseViewModel:IAddExpenseView {

    fun getOnDisMiss():LiveData<Boolean>
    fun getOnDisMissCompleted()
}