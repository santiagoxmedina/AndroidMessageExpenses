package com.sanmed.android.balance.view.adapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.sanmed.android.balance.model.helpers.SummaryItemHelper
import com.sanmed.android.balance.view.summary.ISummaryItemView
import com.sanmed.android.balance.view.summary.SummaryItemAdapter

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

    @BindingAdapter("hideOnEmptyText")
    @JvmStatic
    fun hideOnEmptyText(view: View, text: String?) {
        view.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE

    }

    @BindingAdapter("setError")
    @JvmStatic
    fun setError(view: TextInputLayout, text: String?) {
        view.error = text
    }
}
