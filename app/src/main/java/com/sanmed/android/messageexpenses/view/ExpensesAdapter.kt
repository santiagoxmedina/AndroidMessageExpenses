package com.sanmed.android.messageexpenses.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ExpenseitemBinding
import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding
import com.sanmed.android.messageexpenses.model.entities.Expense

class ExpensesAdapter(diff:DiffExpense) : ListAdapter<IExpense?,ExpensesViewHolder>(diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val binding = DataBindingUtil.inflate<ViewExpenseBinding>(LayoutInflater.from(parent.context),
            R.layout.view_expense, parent, false)
        return ExpensesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}