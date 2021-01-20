package com.sanmed.android.messageexpenses.viewmodel

import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.model.repository.ExpensesRepository
import javax.inject.Inject


class ExpensesViewModel@Inject constructor(
    expensesRepository: ExpensesRepository
)  : ViewModel() {

   /* val expenses: MutableLiveData<List<Expense>> by lazy {
        MutableLiveData<List<Expense>>()
    }*/

    val expenses = expensesRepository.getExpenses()
}