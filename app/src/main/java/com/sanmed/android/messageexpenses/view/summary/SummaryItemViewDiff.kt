package com.sanmed.android.messageexpenses.view.summary

import androidx.recyclerview.widget.DiffUtil

class SummaryItemViewDiff: DiffUtil.ItemCallback<SummaryItemView>() {
    override fun areItemsTheSame(oldItem: SummaryItemView, newItem: SummaryItemView): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SummaryItemView, newItem: SummaryItemView): Boolean {
        return oldItem.amountString == newItem.amountString
    }
}