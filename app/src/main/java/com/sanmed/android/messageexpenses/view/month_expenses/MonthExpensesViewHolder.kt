package com.sanmed.android.messageexpenses.view.month_expenses

import com.sanmed.android.messageexpenses.databinding.ViewMonthAmountBinding
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemViewHolder

class MonthExpensesViewHolder (private val binding: ViewMonthAmountBinding) :
    ISummaryItemViewHolder(binding.root) {

    override fun onBind(item: ISummaryItemView?) {
        item?.let {
            if(item is MonthExpensesUI){
                item.apply {
                    binding.monthName = month
                    binding.amountString = amountString
                    binding.executePendingBindings()
                }
            }
        }
    }
}