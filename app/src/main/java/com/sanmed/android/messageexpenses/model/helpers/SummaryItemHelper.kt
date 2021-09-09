package com.sanmed.android.messageexpenses.model.helpers

import android.database.Cursor
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.model.exeptions.MessageNotSupportedException
import com.sanmed.android.messageexpenses.view.summary.SummaryItemAdapter
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemViewDiff
import com.sanmed.android.messageexpenses.view.utilities.CurrencyUtilities
import com.sanmed.android.messageexpenses.view.utilities.ExpensesTextUtility
import java.lang.Exception
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