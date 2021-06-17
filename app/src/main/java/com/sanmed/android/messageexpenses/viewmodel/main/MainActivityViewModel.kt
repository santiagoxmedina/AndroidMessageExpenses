package com.sanmed.android.messageexpenses.viewmodel.main

import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.model.repository.IExpensesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val expensesRepository: IExpensesRepository):ViewModel(){

    fun  onUpdate(){
        GlobalScope.launch {expensesRepository.updateSummaryExpenses()  }
    }
}