package com.sanmed.android.balance.model.helpers

import com.sanmed.android.balance.model.entities.ExpenseEntity
import com.sanmed.android.balance.model.helpers.CalendarHelper.Companion.convertedDateFormat
import com.sanmed.android.balance.view.expense.ExpenseView
import java.text.SimpleDateFormat
import java.util.*

class CalendarHelper {
    companion object{
        const val BANCOLOMBIA_FORMAT= "H:mm dd/MM/yyyy"
        const val CONVERTED_FORMAT= "H:mm dd/MM/yyyy"
        const val MONTH_FORMAT = "MMMM yyyy"
        const val STRING_TO_CALENDAR_FORMAT = "MMMM yyyy"
        val convertedDateFormat = SimpleDateFormat(BANCOLOMBIA_FORMAT, Locale.ENGLISH)
    }
}
fun String.toCalendar(): Calendar? {
    val calendar = Calendar.getInstance()
    convertedDateFormat.parse(this)?.let {
        calendar.time = it
        return calendar

    }
    return null
}
fun Calendar.toStringCalendar(): String? {
    return convertedDateFormat.format(this.time)
}