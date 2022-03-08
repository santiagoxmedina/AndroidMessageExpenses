package com.sanmed.android.balance.view.summary

import com.sanmed.android.balance.model.helpers.SummaryItemHelper
import com.sanmed.android.balance.view.utilities.CurrencyUtilities
import java.math.BigDecimal
import java.util.*

data class SummaryItemView( val name:String,val amount:BigDecimal,val date:Calendar?):ISummaryItemView {


    val amountString =  CurrencyUtilities.format(amount)
    val dateString =  SummaryItemHelper.getDateString(this)

    override fun getType(): Int {
        return SummaryItemViewType.Expense
    }

    override fun areItemsTheSame(newItem: ISummaryItemView): Boolean {
        if (newItem is SummaryItemView) {
            return name == newItem.name
        }
        return false
    }

    override fun areContentsTheSame(newItem: ISummaryItemView): Boolean {
        if (newItem is SummaryItemView){
            return amountString == newItem.amountString
        }
        return false
    }
}
