package com.sanmed.android.messageexpenses.view.summary

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class SummaryItemAdapter(diffCallback: DiffUtil.ItemCallback<ISummaryItemView>) :
    ListAdapter<ISummaryItemView, ISummaryItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ISummaryItemViewHolder {
        return ISummaryItemViewHolderBuilder.buildType(parent,viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    override fun onBindViewHolder(holder: ISummaryItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}