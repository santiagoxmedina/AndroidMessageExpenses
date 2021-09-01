package com.sanmed.android.messageexpenses.viewmodel.add_category_expense

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.model.action.ActionType
import com.sanmed.android.messageexpenses.model.helpers.CategoryExpenseHelper
import com.sanmed.android.messageexpenses.model.repository.ICategoryExpensesRepository
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import com.sanmed.android.messageexpenses.view.add_category_expense.IAddCategoryExpenseViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class AddCategoryExpenseViewModel @Inject constructor(
    private val categoryExpensesRepository: ICategoryExpensesRepository,
    @ApplicationContext private val context: Context
) :
    IAddCategoryExpenseViewModel {
    private val nameTextMutable = MutableLiveData("")
    private val amountTextMutable = MutableLiveData("")
    private val titleTextMutable = MutableLiveData("")
    private val buttonTextMutable = MutableLiveData("")
    private val _dismiss = MutableLiveData<Boolean>()
    private val _showCategoryExpenseDialog = MutableLiveData<Boolean>()
    private var actionType = ActionType.Add
    private lateinit var actionCategoryExpenseView: ICategoryExpenseView


    override fun getOnDisMiss(): LiveData<Boolean> {
        return _dismiss
    }

    override fun getOnDisMissCompleted() {
        _dismiss.value = false
    }

    override fun onEditCategoryExpense(categoryExpenseView: ICategoryExpenseView) {
        actionType = ActionType.Edit
        actionCategoryExpenseView = categoryExpenseView
        titleTextMutable.value = context.getString(R.string.edit_category_expense)
        buttonTextMutable.value = context.getString(R.string.edit)
        nameTextMutable.value = actionCategoryExpenseView.getName()
        amountTextMutable.value = actionCategoryExpenseView.getAmount().toString()
        _showCategoryExpenseDialog.value = true
    }

    override fun onAddCategoryExpense() {
        actionType = ActionType.Add
        titleTextMutable.value = context.getString(R.string.add_category_expense)
        buttonTextMutable.value = context.getString(R.string.add)
        nameTextMutable.value = ""
        amountTextMutable.value = "0"
        _showCategoryExpenseDialog.value = true
    }

    override fun onAddCategoryExpenseCompleted() {
        _showCategoryExpenseDialog.value = false
    }

    override fun getShowDialog(): LiveData<Boolean> {
        return _showCategoryExpenseDialog
    }


    override fun onCancel() {
        onDismiss()
    }

    override fun onAccept() {
        try {
            GlobalScope.launch {
                when (actionType) {
                    ActionType.Add -> addCategory()
                    ActionType.Edit -> editCategory()
                }
            }
        } catch (nfex: NumberFormatException) {
            Timber.e(nfex)
        }
        onDismiss()
    }

    private suspend fun editCategory() {
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

    private suspend fun addCategory() {
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
        return buttonTextMutable
    }
}