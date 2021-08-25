package com.sanmed.android.messageexpenses.view.summary

import androidx.recyclerview.widget.DiffUtil

class SummaryItemViewDiff: DiffUtil.ItemCallback<ISummaryItemView>() {
    override fun areItemsTheSame(oldItem: ISummaryItemView, newItem: ISummaryItemView): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: ISummaryItemView, newItem: ISummaryItemView): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}