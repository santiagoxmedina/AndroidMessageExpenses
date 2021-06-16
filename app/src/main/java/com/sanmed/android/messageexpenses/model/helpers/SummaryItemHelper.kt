package com.sanmed.android.messageexpenses.model.helpers

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.model.exeptions.MessageNotSupportedException
import com.sanmed.android.messageexpenses.view.summary.SummaryItemAdapter
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemViewDiff
import com.sanmed.android.messageexpenses.view.utilities.CurrencyUtilities
import com.sanmed.android.messageexpenses.view.utilities.ExpensesTextUtility
import java.lang.Exception

object SummaryItemHelper {
    fun createAdapter(): SummaryItemAdapter {
        return SummaryItemAdapter(SummaryItemViewDiff())
    }

    fun parseFromSMS(sms: String?): SummaryItemView? {
        try {
            sms?.let {
                val place = ExpensesTextUtility.getPurchasePlace(sms)
                val price = ExpensesTextUtility.getPurchasePrice(sms)
                val date = ExpensesTextUtility.getPurchaseDate(sms)

                return SummaryItemView(place, CurrencyUtilities.format(price))
            }
        }catch (ex: Exception){
            return null
        }

        return null
    }
}