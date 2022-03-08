package com.sanmed.android.balance.model.helpers

import android.database.Cursor
import com.sanmed.android.balance.view.summary.SummaryItemAdapter
import com.sanmed.android.balance.view.summary.SummaryItemView
import com.sanmed.android.balance.view.summary.SummaryItemViewDiff
import com.sanmed.android.balance.view.utilities.ExpensesTextUtility
import java.text.SimpleDateFormat
import java.util.*

object SummaryItemHelper {

    var dateFormat = SimpleDateFormat("d, MMM, yy", Locale.getDefault())

    fun createAdapter(): SummaryItemAdapter {
        return SummaryItemAdapter(SummaryItemViewDiff())
    }

    fun parseFromSMS(cursor: Cursor): SummaryItemView? {
        try {
            cursor.getString(
                cursor.getColumnIndexOrThrow("body"))?. let {
                    val place = ExpensesTextUtility.getPurchasePlace(it)
                    val price = ExpensesTextUtility.getPurchasePrice(it)
                    val calendar = ExpensesTextUtility.getPurchaseDate(it)
                        ?: getSMSDate(cursor.getLong(cursor.getColumnIndexOrThrow("date")))

                    return SummaryItemView(place, price, calendar)
                }
        } catch (ex: Exception) {
            return null
        }

        return null
    }

    private fun getSMSDate(date: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return calendar
    }

    fun getDateString(item: SummaryItemView): String? {

        return if (item.date != null) {
            dateFormat.format(item.date.time)
        } else {
            "N/A"
        }
    }
}