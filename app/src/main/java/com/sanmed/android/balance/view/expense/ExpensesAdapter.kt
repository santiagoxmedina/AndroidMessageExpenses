package com.sanmed.android.balance.view.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sanmed.android.balance.databinding.ViewExpenseBinding
import com.sanmed.android.balance.model.action.IAction
import com.sanmed.android.balance.view.budget.DiffExpenseView

class ExpensesAdapter (diff: DiffExpenseView, private val onEdit: IAction<String?, View?>): ListAdapter<ExpenseView, ExpensesViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val binding = ViewExpenseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExpensesViewHolder(binding,onEdit)
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}