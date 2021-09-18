package com.sanmed.android.messageexpenses.view.categories_expenses

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.databinding.ViewEditCategoryExpenseBinding

class CategoryExpensesViewHolder(itemView: ViewEditCategoryExpenseBinding) : RecyclerView.ViewHolder(itemView.root) {


    private val mExpenseItemBinding: ViewEditCategoryExpenseBinding = itemView;


    fun bind(categoryExpenseView: ICategoryExpenseView?) {
        if(categoryExpenseView!=null){
            mExpenseItemBinding.expense = categoryExpenseView;
            mExpenseItemBinding.executePendingBindings()
        }
    }

}