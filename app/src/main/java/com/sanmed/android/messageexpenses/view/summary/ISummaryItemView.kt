package com.sanmed.android.messageexpenses.view.summary

interface ISummaryItemView {
    fun getType(): Int
    fun areItemsTheSame(newItem: ISummaryItemView): Boolean
    fun areContentsTheSame(newItem: ISummaryItemView): Boolean
}