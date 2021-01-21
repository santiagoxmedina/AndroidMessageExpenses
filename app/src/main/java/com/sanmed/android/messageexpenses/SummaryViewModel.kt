package com.sanmed.android.messageexpenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.view.ISummaryViewModel

class SummaryViewModel : ViewModel(), ISummaryViewModel {

    private val _navigateToExpenses = MutableLiveData<Boolean>()
    val navigateToExpenses : LiveData<Boolean> get() = _navigateToExpenses

    override fun onNavigateToExpenses() {
        _navigateToExpenses.value = true
    }

    override fun onNavigateToExpensesCompleted() {
        _navigateToExpenses.value = false
    }
}