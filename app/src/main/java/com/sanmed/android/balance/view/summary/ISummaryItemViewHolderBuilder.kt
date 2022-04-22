package com.sanmed.android.balance.view.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sanmed.android.balance.R
import com.sanmed.android.balance.databinding.ViewExpenseBinding
import com.sanmed.android.balance.databinding.ViewMonthAmountBinding
import com.sanmed.android.balance.view.month_expenses.MonthExpensesViewHolder

object ISummaryItemViewHolderBuilder {
    fun buildType(parent: ViewGroup, viewType: Int): ISummaryItemViewHolder{
        return when(viewType){
            SummaryItemViewType.Header->
            MonthExpensesViewHolder(getViewBindingForMonthExpense(parent))
            SummaryItemViewType.Expense->
                SummaryItemViewViewHolder(getViewBindingForExpense(parent))
            else -> throw Exception("Type not supported")
        }
    }

    private fun getViewBindingForExpense(parent: ViewGroup): ViewExpenseBinding {
        return ViewExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    private fun getViewBindingForMonthExpense(parent: ViewGroup): ViewMonthAmountBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_month_amount,
            parent,
            false
        )
    }
}