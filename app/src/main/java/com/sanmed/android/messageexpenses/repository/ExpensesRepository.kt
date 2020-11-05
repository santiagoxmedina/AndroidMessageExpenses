package com.sanmed.android.messageexpenses.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.entities.Expense
import com.sanmed.android.messageexpenses.db.ExpensesDAO
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ExpensesRepository@Inject constructor(
    private val smsReader: SMSReader,
    private val expensesDAO: ExpensesDAO
)  {


    fun getExpenses(): LiveData<List<Expense>>{
        return expensesDAO.load()
    }
}