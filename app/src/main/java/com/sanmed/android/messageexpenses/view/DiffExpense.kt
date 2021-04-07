package com.sanmed.android.messageexpenses.view

import androidx.recyclerview.widget.DiffUtil

class DiffExpense: DiffUtil.ItemCallback<ICategoryExpenseView?>() {

    override fun areItemsTheSame(oldItem: ICategoryExpenseView, newItem: ICategoryExpenseView): Boolean {
       return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: ICategoryExpenseView, newItem: ICategoryExpenseView): Boolean {
        return oldItem.getAmount() == newItem.getAmount() &&
                oldItem.getName() == newItem.getName() &&
                oldItem.getPercentage() == newItem.getPercentage() &&
                oldItem.getType() == newItem.getType()
    }

}