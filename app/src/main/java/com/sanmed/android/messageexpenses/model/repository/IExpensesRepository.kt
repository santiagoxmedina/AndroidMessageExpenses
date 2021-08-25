package com.sanmed.android.messageexpenses.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView

interface IExpensesRepository {
    fun getSummaryExpenses(): LiveData<List<ISummaryItemView>>
    fun updateSummaryExpenses()
    fun onGroupByMonth()
}