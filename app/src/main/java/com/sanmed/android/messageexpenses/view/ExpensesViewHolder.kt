package com.sanmed.android.messageexpenses.view

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding

class ExpensesViewHolder(itemView: ViewExpenseBinding) : RecyclerView.ViewHolder(itemView.root) {


    private val mExpenseItemBinding: ViewExpenseBinding = itemView;


    fun bind(categoryExpenseView: ICategoryExpenseView?) {
        if(categoryExpenseView!=null){
            mExpenseItemBinding.expense = categoryExpenseView;
            mExpenseItemBinding.executePendingBindings()
        }
    }

}