package com.sanmed.android.balance.view.summary

import com.sanmed.android.balance.databinding.ViewExpenseBinding

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