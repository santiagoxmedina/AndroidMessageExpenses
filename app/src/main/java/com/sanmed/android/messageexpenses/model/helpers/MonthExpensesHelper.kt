package com.sanmed.android.messageexpenses.model.helpers

import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import java.util.*

object MonthExpensesHelper {
    fun getMonthId(item: SummaryItemView): String {
        return if(item.date!=null){
            "${item.date.get(Calendar.YEAR)} ${item.date.get(Calendar.MONTH)}"
        }else{
            "N/A"
        }
    }
}