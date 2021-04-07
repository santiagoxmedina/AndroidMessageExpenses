package com.sanmed.android.messageexpenses.view.add_category_expense

import androidx.lifecycle.MutableLiveData

interface IAddCategoryExpenseView {
    fun onCancel()
    fun onAccept()
    fun getNameText():MutableLiveData<String>
    fun getAmountText():MutableLiveData<String>
}