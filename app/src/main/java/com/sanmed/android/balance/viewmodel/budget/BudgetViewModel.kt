package com.sanmed.android.balance.viewmodel.budget

import androidx.lifecycle.*
import com.sanmed.android.balance.model.helpers.toCurrency
import com.sanmed.android.balance.model.repository.ExpensesRepository
import com.sanmed.android.balance.viewmodel.add_edit_expense.IAddEditExpenseViewModel
import com.sanmed.android.balance.view.expense.ExpenseView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val _addExpenseViewModel: IAddEditExpenseViewModel<ExpenseView>,
    private val expensesRepository: ExpensesRepository
) :
    ViewModel(), IBudgetViewModel {


    private val totalAmount = MutableLiveData<BigDecimal>(BigDecimal(700000))
    private val expenses = expensesRepository.getExpenses()
    private val availableAmount = MediatorLiveData<String>()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private fun getTotal(expenses: List<ExpenseView>?): BigDecimal? {
        expenses?.let {
            var total = BigDecimal.ZERO
            it.forEach { expense ->
                total = total.add(expense.amount)
            }
            return total
        }
        return null
    }

    override fun getAvailableAmount(): LiveData<String> {
        return availableAmount
    }

    override fun getExpenses(): LiveData<List<ExpenseView>> {
        return expenses
    }

    override fun addExpense() {
        _addExpenseViewModel.onAddExpense()
    }

    override fun editExpense(id: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                getExpense(id)?.let {
                    withContext(Dispatchers.Main) {
                        _addExpenseViewModel.onEditExpense(it)
                    }
                }
            }
        }
    }

    override fun init() {
        availableAmount.apply {
            fun update() {
                val expenses = getTotal(expenses.value) ?: return
                val amount = totalAmount.value ?: return
                value = (amount - expenses).toCurrency()
            }
            addSource(expenses) { update() }
            addSource(totalAmount) { update() }
            update()
        }
    }

    override fun getTotalAmount(): LiveData<String> {
        return totalAmount.map { it.toCurrency() }
    }

    private fun getExpense(id: String): ExpenseView? {
        expenses.value?.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun getAddExpenseViewModel(): IAddEditExpenseViewModel<ExpenseView> {
        return _addExpenseViewModel
    }

}
