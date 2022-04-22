package com.sanmed.android.balance.view.budget

import androidx.recyclerview.widget.DiffUtil
import com.sanmed.android.balance.view.expense.ExpenseView

class DiffExpenseView : DiffUtil.ItemCallback<ExpenseView?>() {

    override fun areItemsTheSame(oldItem: ExpenseView, newItem: ExpenseView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExpenseView, newItem: ExpenseView): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.amount == newItem.amount &&
                oldItem.date == newItem.date
    }

}