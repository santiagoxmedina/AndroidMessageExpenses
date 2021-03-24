package com.sanmed.android.messageexpenses.view.add_expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.messageexpenses.view.IExpense
import javax.inject.Inject


class AddExpenseViewModel @Inject constructor() :IAddExpenseViewModel{
    private val nameTextMutable = MutableLiveData("")
    private val amountTextMutable = MutableLiveData("")

    private val _dismiss = MutableLiveData<Boolean>()
    override fun getOnDisMiss(): LiveData<Boolean> {
        return _dismiss
    }

    override fun getOnDisMissCompleted() {
        _dismiss.value = false
    }


    override fun onCancel() {
        _dismiss.value = true
    }

    override fun onAccept() {
        _dismiss.value = true
    }


    override fun getNameText(): MutableLiveData<String> {
        return nameTextMutable
    }

    override fun getAmountText(): MutableLiveData<String> {
        return amountTextMutable
    }
}