package com.sanmed.android.messageexpenses.view

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.databinding.ExpenseitemBinding
import com.sanmed.android.messageexpenses.model.entities.Expense

class ExpensesViewHolder(itemView: ExpenseitemBinding) : RecyclerView.ViewHolder(itemView.root) {


    private val mExpenseItemBinding: ExpenseitemBinding = itemView;


    fun bind(expense: Expense) {
        mExpenseItemBinding.textItemDate.text = expense.date.toString()
        mExpenseItemBinding.textItemPlace.text = expense.place
        mExpenseItemBinding.textItemPrice.text = expense.price.toString()
    }
}