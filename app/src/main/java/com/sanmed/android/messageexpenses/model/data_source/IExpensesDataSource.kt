package com.sanmed.android.messageexpenses.model.data_source

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView

interface IExpensesDataSource {
    fun getSummaryExpenses(): LiveData<List<SummaryItemView>>
    fun updateSummaryExpenses()
}