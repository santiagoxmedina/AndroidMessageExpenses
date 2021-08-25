package com.sanmed.android.messageexpenses.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import androidx.databinding.BindingAdapter
import com.sanmed.android.messageexpenses.model.helpers.SummaryItemHelper
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemAdapter

object BindingAdapter {

    @BindingAdapter("setSummaryItemListData")
    @JvmStatic
    fun setSummaryItemListData(view: RecyclerView, data: List<ISummaryItemView>?) {

        if (view.adapter == null) {
            view.adapter = SummaryItemHelper.createAdapter()
        }
        view.adapter?.apply {
            val adapter = this as SummaryItemAdapter
            if (data != null) {
                adapter.submitList(data)
            } else {
                adapter.submitList(emptyList())
            }
        }

    }

    @BindingAdapter("setVisibilityList")
    @JvmStatic
    fun setVisibilityList(view: View, data: List<ISummaryItemView>?) {

        if (data.isNullOrEmpty()) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("setVisibilityListInvert")
    @JvmStatic
    fun setVisibilityListInvert(view: View, data: List<ISummaryItemView>?) {
        if (data.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("setItemAnimator")
    @JvmStatic
    fun setItemAnimator(view: RecyclerView, animator: RecyclerView.ItemAnimator?) {
        animator?.let {
            view.itemAnimator = it
        }
    }
}