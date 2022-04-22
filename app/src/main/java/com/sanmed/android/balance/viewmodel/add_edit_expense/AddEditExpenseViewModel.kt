package com.sanmed.android.balance.viewmodel.add_edit_expense

import android.content.Context
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.balance.R
import com.sanmed.android.balance.model.action.ActionType
import com.sanmed.android.balance.model.helpers.ExpenseHelper
import com.sanmed.android.balance.model.repository.IExpensesRepository
import com.sanmed.android.balance.view.expense.ExpenseView
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class AddEditExpenseViewModel @Inject constructor(
    private val expensesRepository: IExpensesRepository,
    @ApplicationContext private val context: Context
) :
    IAddEditExpenseViewModel<ExpenseView> {
    private val nameTextMutable = MutableLiveData("")
    private val amountTextMutable = MutableLiveData("")
    private val titleTextMutable = MutableLiveData("")
    private val buttonAcceptTextMutable = MutableLiveData("")
    private val buttonCancelTextMutable = MutableLiveData("")
    private val _dismiss = MutableLiveData<Boolean>()
    private val _showCategoryExpenseDialog = MutableLiveData<Boolean>()
    private var actionType = ActionType.Add
    private lateinit var actionExpenseView: ExpenseView
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    override fun getOnDisMiss(): LiveData<Boolean> {
        return _dismiss
    }

    override fun getOnDisMissCompleted() {
        _dismiss.value = false
    }

    override fun getShowDialog(): LiveData<Boolean> {
        return _showCategoryExpenseDialog
    }

    override fun onCancel() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteExpense()
            }
        }
        onDismiss()
    }


    private fun deleteExpense() {
        expensesRepository.delete(actionExpenseView)
    }

    override fun onAccept() {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    when (actionType) {
                        ActionType.Add -> addExpense()
                        ActionType.Edit ->
                            editExpense()
                    }
                }
            }
        } catch (nfex: NumberFormatException) {
            Timber.e(nfex)
        }
        onDismiss()
    }

    override fun onClose() {
        onDismiss()
    }


    private fun editExpense() {
        try {
            val amount = amountTextMutable.value!!.toBigDecimal()
            actionExpenseView.name = nameTextMutable.value!!
            actionExpenseView.amount = amount
            expensesRepository.editExpense(
                actionExpenseView

            )
        } catch (ex: NumberFormatException) {
            //TODO:show error to user
        }
    }

    private fun addExpense() {
        try {
            val amount = amountTextMutable.value!!.toBigDecimal()
            actionExpenseView = ExpenseHelper.create(
                nameTextMutable.value!!, amount
            )
            expensesRepository.addExpense(actionExpenseView)
        } catch (ex: NumberFormatException) {
            //TODO:show error to user
        }

    }

    private fun onDismiss() {
        _dismiss.value = true
    }

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

    override fun onEditExpense(expense: ExpenseView) {
        actionType = ActionType.Edit
        actionExpenseView = expense
        titleTextMutable.value = context.getString(R.string.edit_expense)
        buttonAcceptTextMutable.value = context.getString(R.string.edit)
        buttonCancelTextMutable.value = context.getString(R.string.delete)
        nameTextMutable.value = actionExpenseView.name
        amountTextMutable.value = actionExpenseView.amount.toString()
        _showCategoryExpenseDialog.value = true
    }

    override fun onAddExpense() {
        actionType = ActionType.Add
        titleTextMutable.value = context.getString(R.string.add_expense)
        buttonAcceptTextMutable.value = context.getString(R.string.add)
        buttonCancelTextMutable.value = ""
        nameTextMutable.value = ""
        amountTextMutable.value = "0"
        _showCategoryExpenseDialog.value = true
    }

    override fun onAddExpenseCompleted() {
        _showCategoryExpenseDialog.value = false
    }

    override fun getCancelButtonText(): LiveData<String> {
        return buttonCancelTextMutable
    }
}
