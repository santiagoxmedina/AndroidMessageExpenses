package com.sanmed.android.messageexpenses.viewmodel.summary

import androidx.lifecycle.LiveData
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView

interface ISummaryViewModel {
    fun onNavigateToExpenses();
    fun onNavigateToExpensesCompleted()
    fun getSummaryItemList(): LiveData<List<ISummaryItemView>>
    fun onUpdate();
}