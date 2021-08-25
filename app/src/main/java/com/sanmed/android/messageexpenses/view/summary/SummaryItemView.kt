package com.sanmed.android.messageexpenses.view.summary

data class SummaryItemView( val name:String,val amountString:String):ISummaryItemView {
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
