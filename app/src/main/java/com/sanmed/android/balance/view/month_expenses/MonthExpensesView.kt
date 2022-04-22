package com.sanmed.android.balance.view.month_expenses

import com.sanmed.android.balance.view.summary.ISummaryItemView
import com.sanmed.android.balance.view.summary.SummaryItemView
import com.sanmed.android.balance.view.summary.SummaryItemViewType
import com.sanmed.android.balance.view.utilities.CurrencyUtilities

data class MonthExpensesView(val month:String?="", val expenses:List<SummaryItemView>):ISummaryItemView {

    val amount = expenses.sumOf { it.amount }
    val amountString = CurrencyUtilities.format(amount)

    override fun getType(): Int {
        return SummaryItemViewType.Header
    }

    override fun areItemsTheSame(newItem: ISummaryItemView): Boolean {
        return if(newItem is MonthExpensesView){
            month == newItem.month
        }else{
            false
        }
    }

    override fun areContentsTheSame(newItem: ISummaryItemView): Boolean {
        return if(newItem is MonthExpensesView){
            expenses == newItem.expenses
        }else{
            false
        }
    }
}