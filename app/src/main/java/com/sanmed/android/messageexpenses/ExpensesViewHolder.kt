package com.sanmed.android.messageexpenses

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.entities.Expense
import kotlinx.android.synthetic.main.expenseitem.view.*

class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(expense: Expense) {
        with(itemView) {
            text_item_place.text = expense.place
            text_item_price.text = expense.price.toString()
            text_item_date.text = expense.date.toString()
        }
    }
}