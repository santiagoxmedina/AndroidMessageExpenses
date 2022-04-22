package com.sanmed.android.balance.viewmodel.add_edit_expense

import androidx.lifecycle.LiveData
import com.sanmed.android.balance.viewmodel.expenseDialog.IExpenseDialogViewModel

interface IAddEditExpenseViewModel<T>: IExpenseDialogViewModel {

    fun getOnDisMiss():LiveData<Boolean>
    fun getOnDisMissCompleted()
    fun onEditExpense(expense: T)
    fun onAddExpense()
    fun onAddExpenseCompleted()
    fun getShowDialog(): LiveData<Boolean>
}