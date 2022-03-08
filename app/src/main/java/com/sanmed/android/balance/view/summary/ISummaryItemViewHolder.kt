package com.sanmed.android.balance.view.summary

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ISummaryItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: ISummaryItemView?)
}