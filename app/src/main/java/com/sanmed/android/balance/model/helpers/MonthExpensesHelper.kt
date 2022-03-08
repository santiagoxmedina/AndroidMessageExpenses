package com.sanmed.android.balance.model.helpers

import com.sanmed.android.balance.view.summary.SummaryItemView
import java.text.SimpleDateFormat
import java.util.*

object MonthExpensesHelper {
    var dateFormatter:SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    fun getMonthId(item: SummaryItemView): String {
        return if(item.date!=null){
            dateFormatter.format(item.date.time).toUpperCase(Locale.getDefault())
        }else{
            "N/A"
        }
    }
}