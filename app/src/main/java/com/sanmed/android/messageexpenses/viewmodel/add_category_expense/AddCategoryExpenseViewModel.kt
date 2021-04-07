package com.sanmed.android.messageexpenses.viewmodel.add_category_expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.messageexpenses.model.helpers.CategoryExpenseHelper
import com.sanmed.android.messageexpenses.model.repository.ICategoryExpensesRepository
import com.sanmed.android.messageexpenses.view.add_category_expense.IAddCategoryExpenseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class AddCategoryExpenseViewModel @Inject constructor(private val categoryExpensesRepository: ICategoryExpensesRepository) :
    IAddCategoryExpenseViewModel {
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
        onDismiss()
    }

    override fun onAccept() {
        try {
            val amount = amountTextMutable.value!!.toFloat()
                GlobalScope.launch {
                    categoryExpensesRepository.addCategory(
                        CategoryExpenseHelper.create(
                            nameTextMutable.value!!,amount

                        )
                    )
                }
        }catch (nfex:NumberFormatException  ){
            Timber.e(nfex)
        }
        onDismiss()
    }

    private fun onDismiss() {
        _dismiss.value = true
        nameTextMutable.value = ""
        amountTextMutable.value = ""
    }

    override fun getNameText(): MutableLiveData<String> {
        return nameTextMutable
    }

    override fun getAmountText(): MutableLiveData<String> {
        return amountTextMutable
    }
}