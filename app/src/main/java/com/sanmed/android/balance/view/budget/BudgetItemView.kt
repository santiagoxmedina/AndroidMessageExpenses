package com.sanmed.android.balance.view.budget

import com.sanmed.android.balance.view.summary.ISummaryItemView
import com.sanmed.android.balance.view.summary.SummaryItemViewType

class BudgetItemView: ISummaryItemView {
    override fun getType(): Int {
        return SummaryItemViewType.Expense
    }

    override fun areItemsTheSame(newItem: ISummaryItemView): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(newItem: ISummaryItemView): Boolean {
        TODO("Not yet implemented")
    }
}