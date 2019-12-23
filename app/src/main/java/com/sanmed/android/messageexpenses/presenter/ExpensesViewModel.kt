package com.sanmed.android.messageexpenses.presenter

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.messageexpenses.entities.Expense
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.sanmed.android.messageexpenses.MainActivity


class ExpensesViewModel(application: Application) : AndroidViewModel(application) {

    val expenses: MutableLiveData<List<Expense>> by lazy {
        MutableLiveData<List<Expense>>()
    }
}