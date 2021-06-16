package com.sanmed.android.messageexpenses.model.expensess_parsers

import java.util.*

open class EmptyParser:IExpensesParser {

    private val calendar = Calendar.getInstance()
    override fun getPurchasePrice(message: String): Float {
        return 0F
    }

    override fun getPurchasePlace(message: String): String {
        return ""
    }

    override fun getPurchaseDate(message: String): Date {
        return calendar.time
    }

    override fun getPurchaseCardNumber(message: String): String {
        return ""
    }

    override fun getPurchaseCardType(message: String): String {
        return ""
    }
}