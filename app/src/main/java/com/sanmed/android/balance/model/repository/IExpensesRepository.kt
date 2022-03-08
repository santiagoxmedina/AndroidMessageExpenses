package com.sanmed.android.balance.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.balance.view.summary.ISummaryItemView

interface IExpensesRepository {
    fun getSummaryExpenses(): LiveData<List<ISummaryItemView>>
    fun updateSummaryExpenses()
    fun onGroupByMonth()
}