package com.sanmed.android.messageexpenses.model.helpers

import androidx.room.util.StringUtil
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

object MonthExpensesHelper {
    var dateFormatter:SimpleDateFormat = SimpleDateFormat("MMM, yy", Locale.getDefault())
    fun getMonthId(item: SummaryItemView): String {
        return if(item.date!=null){
            dateFormatter.format(item.date.time).toUpperCase(Locale.getDefault())
        }else{
            "N/A"
        }
    }
}