package com.sanmed.android.messageexpenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.entities.Expense

class ExpensesAdapter : RecyclerView.Adapter<ExpensesViewHolder>() {

    var expenses = mutableListOf<Expense>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expenseitem, parent, false)
        return ExpensesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.bind(expenses[position])
    }
}