package com.sanmed.android.messageexpenses.view

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.databinding.ExpenseitemBinding
import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding
import com.sanmed.android.messageexpenses.model.entities.Expense

class ExpensesViewHolder(itemView: ViewExpenseBinding) : RecyclerView.ViewHolder(itemView.root) {


    private val mExpenseItemBinding: ViewExpenseBinding = itemView;


    fun bind(expense: IExpense?) {
        if(expense!=null){
            mExpenseItemBinding.expense = expense;
            mExpenseItemBinding.executePendingBindings()
        }
    }

}