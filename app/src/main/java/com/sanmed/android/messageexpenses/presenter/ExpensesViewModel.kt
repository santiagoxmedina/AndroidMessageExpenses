package com.sanmed.android.messageexpenses.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.entities.Expense
import com.sanmed.android.messageexpenses.repository.ExpensesRepository
import javax.inject.Inject


class ExpensesViewModel@Inject constructor(
    expensesRepository: ExpensesRepository
)  : ViewModel() {

   /* val expenses: MutableLiveData<List<Expense>> by lazy {
        MutableLiveData<List<Expense>>()
    }*/

    val expenses = expensesRepository.getExpenses()
}