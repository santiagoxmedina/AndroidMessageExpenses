package com.sanmed.android.messageexpenses.view.month_expenses

import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemViewType
import com.sanmed.android.messageexpenses.view.utilities.CurrencyUtilities

data class MonthExpensesUI(val month:String?="",val expenses:List<SummaryItemView>):ISummaryItemView {

    val amount = expenses.sumOf { it.amount }
    val amountString = CurrencyUtilities.format(amount)

    override fun getType(): Int {
        return SummaryItemViewType.Header
    }

    override fun areItemsTheSame(newItem: ISummaryItemView): Boolean {
        return if(newItem is MonthExpensesUI){
            month == newItem.month
        }else{
            false
        }
    }

    override fun areContentsTheSame(newItem: ISummaryItemView): Boolean {
        return if(newItem is MonthExpensesUI){
            expenses == newItem.expenses
        }else{
            false
        }
    }
}