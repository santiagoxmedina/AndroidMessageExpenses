package com.sanmed.android.messageexpenses.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding

class ExpensesAdapter(diff:DiffExpense) : ListAdapter<ICategoryExpenseView?,ExpensesViewHolder>(diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val binding = DataBindingUtil.inflate<ViewExpenseBinding>(LayoutInflater.from(parent.context),
            R.layout.view_expense, parent, false)
        return ExpensesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}