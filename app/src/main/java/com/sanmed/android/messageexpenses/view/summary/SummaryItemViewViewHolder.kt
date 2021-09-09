package com.sanmed.android.messageexpenses.view.summary

import com.sanmed.android.messageexpenses.databinding.ViewExpenseBinding
import com.sanmed.android.messageexpenses.model.helpers.SummaryItemHelper

class SummaryItemViewViewHolder(private val binding: ViewExpenseBinding) :
    ISummaryItemViewHolder(binding.root) {

    override fun onBind(item: ISummaryItemView?) {
        item?.let {
            if(item is SummaryItemView){
                item.apply {
                    binding.name = name
                    binding.amountString = amountString
                    binding.dateString = dateString
                    binding.executePendingBindings()
                }
            }
        }
    }
}