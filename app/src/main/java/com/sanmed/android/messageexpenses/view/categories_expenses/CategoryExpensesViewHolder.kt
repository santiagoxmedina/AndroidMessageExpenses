package com.sanmed.android.messageexpenses.view.categories_expenses

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView

class CategoryExpensesViewHolder(itemView: ViewExpenseBinding) : RecyclerView.ViewHolder(itemView.root) {


    private val mExpenseItemBinding: ViewExpenseBinding = itemView;


    fun bind(categoryExpenseView: ICategoryExpenseView?) {
        if(categoryExpenseView!=null){
            mExpenseItemBinding.expense = categoryExpenseView;
            mExpenseItemBinding.executePendingBindings()
        }
    }

}