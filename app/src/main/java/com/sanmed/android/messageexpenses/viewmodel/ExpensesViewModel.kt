package com.sanmed.android.messageexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.view.ExpenseView
import com.sanmed.android.messageexpenses.view.IExpense

class ExpensesViewModel:ViewModel() {

    private val _expenses = MutableLiveData<List<IExpense?>>()
    val expenses: LiveData<List<IExpense?>>
    get() = _expenses


    fun onAddExpense(){

        val list = mutableListOf<IExpense?>()
        _expenses.value?.let { list.addAll(it) }
        list.add(getNewExpenseTest(list.size))
        _expenses.value = list

    }

    private fun getNewExpenseTest(index:Int): IExpense {
        return ExpenseView(1000,"SANMED",0.3f,0,index);
    }

}