package com.sanmed.android.balance.viewmodel.expenseDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open abstract class ExpenseDialogViewModel:IExpenseDialogViewModel {

    protected val nameTextMutable = MutableLiveData("")
    protected val nameErrorTextMutable = MutableLiveData("")
    protected val amountTextMutable = MutableLiveData("")
    protected val amountErrorTextMutable = MutableLiveData("")
    protected val titleTextMutable = MutableLiveData("")
    protected val buttonAcceptTextMutable = MutableLiveData("")
    protected val buttonCancelTextMutable = MutableLiveData("")

    override fun getNameText(): MutableLiveData<String> {
        return nameTextMutable
    }

    override fun getAmountText(): MutableLiveData<String> {
        return amountTextMutable
    }

    override fun getTitle(): LiveData<String> {
        return titleTextMutable
    }

    override fun getOkButtonText(): LiveData<String> {
        return buttonAcceptTextMutable
    }

    override fun getCancelButtonText(): LiveData<String> {
        return buttonCancelTextMutable
    }

    override fun getNameErrorText(): LiveData<String> {
        return nameErrorTextMutable
    }

    override fun getAmountErrorText(): LiveData<String> {
        return amountErrorTextMutable
    }
}