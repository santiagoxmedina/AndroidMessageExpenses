package com.sanmed.android.messageexpenses.viewmodel.main

import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.model.repository.IExpensesRepository
import com.sanmed.android.messageexpenses.viewmodel.summary.SummaryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val expensesRepository: IExpensesRepository) :
    SummaryViewModel(expensesRepository) {

    fun onGroupByMonth() {
        GlobalScope.launch { expensesRepository.onGroupByMonth() }
    }
}