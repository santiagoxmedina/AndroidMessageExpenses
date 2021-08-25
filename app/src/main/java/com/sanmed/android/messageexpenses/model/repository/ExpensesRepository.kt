package com.sanmed.android.messageexpenses.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.model.data_source.IExpensesDataSource
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import javax.inject.Inject

class ExpensesRepository @Inject constructor(private val expensesDatasource: IExpensesDataSource) :
    IExpensesRepository {
    override fun getSummaryExpenses(): LiveData<List<ISummaryItemView>> {
        return expensesDatasource.getSummaryExpenses()
    }

    override fun updateSummaryExpenses() {
        expensesDatasource.updateSummaryExpenses()
    }

    override fun onGroupByMonth() {
        expensesDatasource.onGroupByMonth()
    }
}