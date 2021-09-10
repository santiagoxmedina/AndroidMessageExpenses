package com.sanmed.android.messageexpenses.view.summary

import androidx.lifecycle.LiveData

interface ISummaryViewModel {
    fun onNavigateToExpenses();
    fun onNavigateToExpensesCompleted()
    fun getSummaryItemList(): LiveData<List<ISummaryItemView>>
    fun onUpdate();
}