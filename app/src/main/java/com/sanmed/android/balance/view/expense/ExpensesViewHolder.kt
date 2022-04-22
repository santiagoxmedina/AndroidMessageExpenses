package com.sanmed.android.balance.view.expense

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.balance.databinding.ViewExpenseBinding
import com.sanmed.android.balance.model.action.IAction
import com.sanmed.android.balance.model.helpers.toCurrency
import com.sanmed.android.balance.model.helpers.toStringCalendar

class ExpensesViewHolder(private val binding: ViewExpenseBinding, onEdit: IAction<String?, View?>) :
    RecyclerView.ViewHolder(binding.root) {

    private var expense:ExpenseView? = null

    init {
        binding.root.setOnClickListener {viewClick->
            expense?.let {expenseView->
                onEdit.onAction(expenseView.id,viewClick)
            }
        }
    }

    fun bind(_expense: ExpenseView) {
        expense = _expense
        binding.name.text = expense?.name
        binding.amount.text = expense?.amount?.toCurrency()
        binding.date.text = expense?.date?.toStringCalendar()
    }
}