package com.sanmed.android.messageexpenses.view.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ViewNameAmountBinding

object ISummaryItemViewHolderBuilder {
    fun buildType(parent: ViewGroup, viewType: Int): ISummaryItemViewHolder{
        if(viewType == SummaryItemViewType.Header){
            return SummaryItemViewViewHolder(getViewBindingForExpense(parent))
        }else if(viewType == SummaryItemViewType.Expense){
            return SummaryItemViewViewHolder(getViewBindingForExpense(parent))
        }else{
            throw Exception("Type not supported")
        }
    }

    private fun getViewBindingForExpense(parent: ViewGroup): ViewNameAmountBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_name_amount,
            parent,
            false
        )
    }
}