package com.sanmed.android.messageexpenses.view.categories_expenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding
import com.sanmed.android.messageexpenses.model.action.IAction
import com.sanmed.android.messageexpenses.view.DiffExpense
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView

class CategoryExpensesAdapter(diff: DiffExpense,private val onEdit:IAction<ICategoryExpenseView,View>) : ListAdapter<ICategoryExpenseView?, CategoryExpensesViewHolder>(diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryExpensesViewHolder {
        val binding = DataBindingUtil.inflate<ViewExpenseBinding>(LayoutInflater.from(parent.context),
            R.layout.view_expense, parent, false)
        binding.onEdit=onEdit
        return CategoryExpensesViewHolder(binding)
    }

    override fun onBindViewHolder(holderCategory: CategoryExpensesViewHolder, position: Int) {
        holderCategory.bind(getItem(position))
    }
}