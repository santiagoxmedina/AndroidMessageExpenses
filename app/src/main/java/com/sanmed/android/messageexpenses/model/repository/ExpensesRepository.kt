package com.sanmed.android.messageexpenses.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.model.entities.Expense
import com.sanmed.android.messageexpenses.model.db.ExpensesDAO
import javax.inject.Inject

class ExpensesRepository@Inject constructor(
    private val smsReader: SMSReader,
    private val expensesDAO: ExpensesDAO
)  {


    fun getExpenses(): LiveData<List<Expense>>{
        return expensesDAO.load()
    }
}