package com.sanmed.android.balance.viewmodel.summary

import androidx.lifecycle.LiveData
import com.sanmed.android.balance.view.summary.ISummaryItemView

interface ISummaryViewModel {
    fun onNavigateToExpenses();
    fun onNavigateToExpensesCompleted()
    fun getSummaryItemList(): LiveData<List<ISummaryItemView>>
    fun onUpdate();
}