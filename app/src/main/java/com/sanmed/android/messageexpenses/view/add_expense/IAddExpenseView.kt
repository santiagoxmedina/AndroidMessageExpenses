package com.sanmed.android.messageexpenses.view.add_expense

import androidx.lifecycle.MutableLiveData

interface IAddExpenseView {
    fun onCancel()
    fun onAccept()
    fun getNameText():MutableLiveData<String>
    fun getAmountText():MutableLiveData<String>
}