package com.sanmed.android.messageexpenses.model.data_source

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView

interface IExpensesDataSource {
    fun getSummaryExpenses(): LiveData<List<ISummaryItemView>>
    fun updateSummaryExpenses()
    fun onGroupByMonth()
}