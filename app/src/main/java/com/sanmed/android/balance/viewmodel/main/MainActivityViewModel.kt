package com.sanmed.android.balance.viewmodel.main

import com.sanmed.android.balance.model.repository.IExpensesRepository
import com.sanmed.android.balance.viewmodel.summary.SummaryViewModel
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