package com.sanmed.android.balance.viewmodel.expenseDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IExpenseDialogViewModel {
    fun onCancel()
    fun onAccept()
    fun onClose()
    fun getNameText():MutableLiveData<String>
    fun getNameErrorText():LiveData<String>
    fun getAmountText():MutableLiveData<String>
    fun getAmountErrorText():LiveData<String>
    fun getTitle(): LiveData<String>
    fun getOkButtonText(): LiveData<String>
    fun getCancelButtonText(): LiveData<String>
}