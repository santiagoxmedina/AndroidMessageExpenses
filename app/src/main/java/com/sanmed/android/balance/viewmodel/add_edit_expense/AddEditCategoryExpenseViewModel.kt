package com.sanmed.android.balance.viewmodel.add_edit_expense

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.balance.R
import com.sanmed.android.balance.model.action.ActionType
import com.sanmed.android.balance.model.helpers.CategoryExpenseHelper
import com.sanmed.android.balance.model.repository.ICategoryExpensesRepository
import com.sanmed.android.balance.view.categories_expenses.ICategoryExpenseView
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class AddEditCategoryExpenseViewModel @Inject constructor(
    private val categoryExpensesRepository: ICategoryExpensesRepository,
    @ApplicationContext private val context: Context
) :
    IAddEditExpenseViewModel<ICategoryExpenseView> {
    private val nameTextMutable = MutableLiveData("")
    private val amountTextMutable = MutableLiveData("")
    private val titleTextMutable = MutableLiveData("")
    private val buttonAcceptTextMutable = MutableLiveData("")
    private val buttonCancelTextMutable = MutableLiveData("")
    private val _dismiss = MutableLiveData<Boolean>()
    private val _showCategoryExpenseDialog = MutableLiveData<Boolean>()
    private var actionType = ActionType.Add
    private lateinit var actionCategoryExpenseView: ICategoryExpenseView
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

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
        deleteCategory()
        onDismiss()
    }

    override fun onAccept() {
        try {
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    when (actionType) {
                        ActionType.Add -> addCategory()
                        ActionType.Edit -> editCategory()
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

    private fun editCategory() {
        try {
            val amount = amountTextMutable.value!!.toBigDecimal()
            actionCategoryExpenseView.setName(nameTextMutable.value!!)
            actionCategoryExpenseView.setAmount(amount)
            categoryExpensesRepository.editCategory(
                actionCategoryExpenseView

            )
        } catch (ex: NumberFormatException) {
            //TODO:show error to user
        }
    }

    private fun addCategory() {
        try {
            val amount = amountTextMutable.value!!.toBigDecimal()
            actionCategoryExpenseView = CategoryExpenseHelper.create(
                nameTextMutable.value!!, amount
            )
            categoryExpensesRepository.addCategory(actionCategoryExpenseView)
        } catch (ex: NumberFormatException) {
            //TODO:show error to user
        }

    }

    private fun deleteCategory() {
        //do  nothing
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

    override fun onEditExpense(expense: ICategoryExpenseView) {
        actionType = ActionType.Edit
        actionCategoryExpenseView = expense
        titleTextMutable.value = context.getString(R.string.edit_category_expense)
        buttonAcceptTextMutable.value = context.getString(R.string.edit)
        buttonCancelTextMutable.value = context.getString(R.string.delete)
        nameTextMutable.value = actionCategoryExpenseView.getName()
        amountTextMutable.value = actionCategoryExpenseView.getAmount().toString()
        _showCategoryExpenseDialog.value = true
    }

    override fun onAddExpense() {
        actionType = ActionType.Add
        titleTextMutable.value = context.getString(R.string.add_category_expense)
        buttonAcceptTextMutable.value = context.getString(R.string.add)
        nameTextMutable.value = ""
        amountTextMutable.value = "0"
        _showCategoryExpenseDialog.value = true
        buttonCancelTextMutable.value = ""
    }

    override fun onAddExpenseCompleted() {
        _showCategoryExpenseDialog.value = false
    }

    override fun getCancelButtonText(): LiveData<String> {
        return buttonCancelTextMutable
    }
}
