package com.sanmed.android.balance.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.android.balance.model.data_source.IExpensesDataSource
import com.sanmed.android.balance.view.expense.ExpenseView
import com.sanmed.android.balance.view.summary.ISummaryItemView
import javax.inject.Inject

class ExpensesRepository @Inject constructor(private val expensesDatasource: IExpensesDataSource) :
    IExpensesRepository {
    override fun getSummaryExpenses(): LiveData<List<ISummaryItemView>> {
        return expensesDatasource.getSummaryExpenses()
    }

    override fun updateSummaryExpenses() {
        expensesDatasource.updateSummaryExpenses()
    }

    override fun onGroupByMonth() {
        expensesDatasource.onGroupByMonth()
    }

    override fun delete(expense: ExpenseView) {
        expensesDatasource.delete(expense)
    }

    override fun getExpenses(): LiveData<List<ExpenseView>> {
        return expensesDatasource.getExpenses()
    }

    override fun editExpense(expense: ExpenseView) {
        expensesDatasource.editExpense(expense)
    }

    override fun addExpense(expense: ExpenseView) {
        expensesDatasource.addExpense(expense)
    }
}