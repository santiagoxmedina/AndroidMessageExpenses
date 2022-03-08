package com.sanmed.android.balance.model.parsers

import java.math.BigDecimal
import java.util.*

open class EmptyParser:IExpensesParser {

    private val calendar = Calendar.getInstance()
    override fun getPurchasePrice(message: String): BigDecimal {
        return BigDecimal.ZERO
    }

    override fun getPurchasePlace(message: String): String {
        return ""
    }

    override fun getPurchaseDate(message: String): Calendar?  {
        return null
    }

    override fun getPurchaseCardNumber(message: String): String {
        return ""
    }

    override fun getPurchaseCardType(message: String): String {
        return ""
    }
}