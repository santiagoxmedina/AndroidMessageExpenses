package com.sanmed.android.messageexpenses.view.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ViewNameAmountBinding

class SummaryItemAdapter(diffCallback: DiffUtil.ItemCallback<SummaryItemView>): ListAdapter<SummaryItemView, SummaryItemViewViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryItemViewViewHolder {
        return SummaryItemViewViewHolder(getViewBinding(parent))
    }

    private fun getViewBinding(parent: ViewGroup): ViewNameAmountBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_name_amount,parent,false)
    }

    override fun onBindViewHolder(holder: SummaryItemViewViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}