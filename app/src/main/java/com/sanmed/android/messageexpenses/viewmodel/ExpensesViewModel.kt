package com.sanmed.android.messageexpenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.view.ExpenseView
import com.sanmed.android.messageexpenses.view.IExpense
import com.sanmed.android.messageexpenses.view.add_expense.IAddExpenseView
import com.sanmed.android.messageexpenses.view.add_expense.IAddExpenseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(private val _addExpenseViewModel:IAddExpenseViewModel ):ViewModel() {

    private val _expenses = MutableLiveData<List<IExpense?>>()
    val expenses: LiveData<List<IExpense?>>
    get() = _expenses

    private val _addExpense = MutableLiveData<Boolean>()
    val addExpense: LiveData<Boolean>
        get() = _addExpense

    fun onAddExpense(){
        _addExpense.value = true
//        val list = mutableListOf<IExpense?>()
//        _expenses.value?.let { list.addAll(it) }
//        list.add(getNewExpenseTest(list.size))
//        _expenses.value = list

    }

    fun onAddExpenseCompleted(){
        _addExpense.value = false
    }

    private fun getNewExpenseTest(index:Int): IExpense {
        return ExpenseView(1000,"SANMED",0.3f,0,index);
    }

    fun getAddExpenseViewModel(): IAddExpenseViewModel {
        return _addExpenseViewModel
    }

}