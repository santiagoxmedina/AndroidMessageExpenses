package com.sanmed.android.balance.view.month_expenses

import com.sanmed.android.balance.databinding.ViewMonthAmountBinding
import com.sanmed.android.balance.view.summary.ISummaryItemView
import com.sanmed.android.balance.view.summary.ISummaryItemViewHolder

class MonthExpensesViewHolder (private val binding: ViewMonthAmountBinding) :
    ISummaryItemViewHolder(binding.root) {

    override fun onBind(item: ISummaryItemView?) {
        item?.let {
            if(item is MonthExpensesView){
                item.apply {
                    binding.monthName = month
                    binding.amountString = amountString
                    binding.executePendingBindings()
                }
            }
        }
    }
}