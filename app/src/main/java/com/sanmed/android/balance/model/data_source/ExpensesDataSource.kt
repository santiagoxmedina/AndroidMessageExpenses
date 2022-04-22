package com.sanmed.android.balance.model.data_source

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.sanmed.android.balance.model.db.CategoryExpenseDAO
import com.sanmed.android.balance.model.db.ExpenseDAO
import com.sanmed.android.balance.model.entities.CategoryExpenseEntity
import com.sanmed.android.balance.model.entities.ExpenseEntity
import com.sanmed.android.balance.model.helpers.*
import com.sanmed.android.balance.view.expense.ExpenseView
import com.sanmed.android.balance.view.month_expenses.MonthExpensesView
import com.sanmed.android.balance.view.summary.ISummaryItemView
import com.sanmed.android.balance.view.summary.SummaryItemView
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpensesDataSource @Inject constructor(@ApplicationContext private val context: Context,private val expensesDAO: ExpenseDAO) :
    IExpensesDataSource {
    val expenses = MutableLiveData<List<ISummaryItemView>>()
    val _expenses: LiveData<List<ISummaryItemView>> get() = expenses
    var expensesList: List<SummaryItemView>? = null
    private val allExpenses: Flow<List<ExpenseEntity>> = expensesDAO.getAll()

    override fun getSummaryExpenses(): LiveData<List<ISummaryItemView>> {
        return _expenses
    }

    override fun updateSummaryExpenses() {
        val cursor: Cursor? =
            context.contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)
        val result = mutableListOf<SummaryItemView>()
        cursor?.apply {
            if (moveToFirst()) {
                do {
                    SummaryItemHelper.parseFromSMS(
                        cursor
                    )?.let {
                        result.add(it)
                    }
                } while (moveToNext())
            }
        }?.close()
        expensesList = result;
        expenses.postValue(expensesList)
    }

    override fun onGroupByMonth() {
        val monthExpenses: List<ISummaryItemView> = getExpensesGroupByMonth(expensesList)
        expenses.postValue(monthExpenses)
    }

    override fun delete(expense: ExpenseView) {
        expensesDAO.delete(expense.toEntity())
    }

    override fun getExpenses(): LiveData<List<ExpenseView>> {
        return Transformations.map(
            allExpenses.asLiveData(),
            ExpenseHelper::getListExpenseEntityFromListExpenseView
        )
    }

    override fun editExpense(expense: ExpenseView) {
        expensesDAO.update(expense.toEntity())
    }

    override fun addExpense(expense: ExpenseView) {
        expensesDAO.save(expense.toEntity())
    }

    private fun getExpensesGroupByMonth(allExpenses: List<SummaryItemView>?): List<ISummaryItemView> {
        val result = mutableListOf<ISummaryItemView>()
        allExpenses?.let { list ->
            val monthExpensesMap = list.groupBy({ MonthExpensesHelper.getMonthId(it) }, { it })
            monthExpensesMap.forEach { (month, monthList) ->
                result.add(MonthExpensesView(month, monthList))
            }
        }
        return result;
    }
}


