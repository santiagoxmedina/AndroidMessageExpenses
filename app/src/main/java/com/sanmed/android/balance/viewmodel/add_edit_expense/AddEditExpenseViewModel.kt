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
import com.sanmed.android.balance.viewmodel.expenseDialog.ExpenseDialogViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject

class AddEditExpenseViewModel @Inject constructor(
    private val expensesRepository: IExpensesRepository,
    @ApplicationContext private val context: Context
) : ExpenseDialogViewModel(),
    IAddEditExpenseViewModel<ExpenseView> {

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

    private fun checkValidData(success: (name: String, amount: BigDecimal) -> Unit) {
        val name = nameTextMutable.value
        var amount = amountTextMutable.value?.toBigDecimalOrNull()
        when {
            !isValidName(name) -> nameErrorTextMutable.value =
                context.getString(R.string.invalid_name)
            !isValidAmount(amount) -> amountErrorTextMutable.value =
                context.getString(R.string.invalid_amount)
            else -> {
                success(name!!, amount!!)
                onDismiss()
            }
        }
    }

    override fun onAccept() {
        checkValidData { name, amount ->
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    when (actionType) {
                        ActionType.Add -> addExpense(name, amount)
                        ActionType.Edit ->
                            editExpense(name, amount)
                    }
                }
            }
        }
    }

    override fun onClose() {
        onDismiss()
    }


    private fun editExpense(_name: String, _amount: BigDecimal) {
        actionExpenseView.apply {
            name = _name
            amount = _amount
            expensesRepository.editExpense(
                actionExpenseView

            )
        }
    }

    private fun addExpense(name: String, amount: BigDecimal) {
        expensesRepository.addExpense(
            ExpenseHelper.create(
                name, amount
            )
        )
    }

    private fun isValidName(value: String?): Boolean {
        return !value.isNullOrEmpty()
    }

    private fun isValidAmount(amount: BigDecimal?): Boolean {
        amount?.let {
            return amount!= BigDecimal.ZERO && amount.toString().isNotEmpty()
        }
        return false
    }

    private fun onDismiss() {
        nameErrorTextMutable.value = ""
        amountErrorTextMutable.value = ""
        _dismiss.value = true
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
        amountErrorTextMutable.value = ""
        nameErrorTextMutable.value = ""
    }

    override fun onAddExpense() {
        actionType = ActionType.Add
        titleTextMutable.value = context.getString(R.string.add_expense)
        buttonAcceptTextMutable.value = context.getString(R.string.add)
        buttonCancelTextMutable.value = ""
        nameTextMutable.value = ""
        amountTextMutable.value = "0"
        _showCategoryExpenseDialog.value = true
        amountErrorTextMutable.value = ""
        nameErrorTextMutable.value = ""
    }

    override fun onAddExpenseCompleted() {
        _showCategoryExpenseDialog.value = false
    }


}
