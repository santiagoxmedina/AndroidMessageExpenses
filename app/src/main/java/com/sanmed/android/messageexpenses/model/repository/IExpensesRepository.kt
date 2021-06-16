package com.sanmed.android.messageexpenses.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView

interface IExpensesRepository {
    fun getSummaryExpenses(): LiveData<List<SummaryItemView>>
    fun updateSummaryExpenses()
}