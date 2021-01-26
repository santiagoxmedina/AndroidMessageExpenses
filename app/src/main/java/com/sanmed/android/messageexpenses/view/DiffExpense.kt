package com.sanmed.android.messageexpenses.view

import androidx.recyclerview.widget.DiffUtil

class DiffExpense: DiffUtil.ItemCallback<IExpense?>() {

    override fun areItemsTheSame(oldItem: IExpense, newItem: IExpense): Boolean {
       return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: IExpense, newItem: IExpense): Boolean {
        return oldItem.getAmount() == newItem.getAmount() &&
                oldItem.getName() == newItem.getName() &&
                oldItem.getPercentage() == newItem.getPercentage() &&
                oldItem.getType() == newItem.getType()
    }

}