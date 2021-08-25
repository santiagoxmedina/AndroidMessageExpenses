package com.sanmed.android.messageexpenses.view.summary

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.databinding.ViewNameAmountBinding

class SummaryItemViewViewHolder(private val binding: ViewNameAmountBinding) :
    ISummaryItemViewHolder(binding.root) {

    override fun onBind(item: ISummaryItemView?) {
        item?.let {
            if(item is SummaryItemView){
                item.apply {
                    binding.name = name
                    binding.amountString = amountString
                    binding.executePendingBindings()
                }
            }
        }
    }
}