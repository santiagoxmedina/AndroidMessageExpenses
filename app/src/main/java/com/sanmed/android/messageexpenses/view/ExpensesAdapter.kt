package com.sanmed.android.messageexpenses.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ExpenseitemBinding
import com.sanmed.android.messageexpenses.model.entities.Expense

class ExpensesAdapter : RecyclerView.Adapter<ExpensesViewHolder>() {

    var expenses = mutableListOf<Expense>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val binding = DataBindingUtil.inflate<ExpenseitemBinding>(LayoutInflater.from(parent.context),
            R.layout.expenseitem, parent, false)
        return ExpensesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.bind(expenses[position])
    }
}