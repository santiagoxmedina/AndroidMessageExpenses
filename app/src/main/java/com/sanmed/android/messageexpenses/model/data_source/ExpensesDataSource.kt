package com.sanmed.android.messageexpenses.model.data_source

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.messageexpenses.model.helpers.MonthExpensesHelper
import com.sanmed.android.messageexpenses.model.helpers.SummaryItemHelper
import com.sanmed.android.messageexpenses.view.month_expenses.MonthExpensesUI
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExpensesDataSource @Inject constructor(@ApplicationContext private val context: Context) :
    IExpensesDataSource {
    val expenses = MutableLiveData<List<ISummaryItemView>>()
    val _expenses: LiveData<List<ISummaryItemView>> get() = expenses
    var expensesList: List<SummaryItemView>? = null
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
                    var msgData = ""
                    for (idx in 0 until columnCount) {
                        var summaryItemView: SummaryItemView? = SummaryItemHelper.parseFromSMS(
                            getString(
                                idx
                            )
                        )
                        summaryItemView?.let {
                            result.add(it)
                        }
                    }
                    // use msgData
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

    private fun getExpensesGroupByMonth(allExpenses: List<SummaryItemView>?): List<ISummaryItemView> {
        val result = mutableListOf<ISummaryItemView>()
        allExpenses?.let {list->
            val monthExpensesMap = list.groupBy({MonthExpensesHelper.getMonthId(it)},{it})
            monthExpensesMap.forEach { (month, monthList) ->
                result.add(MonthExpensesUI(month,monthList))
            }
        }
        return result;
    }
}