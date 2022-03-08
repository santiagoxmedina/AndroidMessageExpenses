package com.sanmed.android.balance.model.parsers

import java.math.BigDecimal
import java.util.*

interface IExpensesParser {
    fun getPurchasePrice(message: String): BigDecimal
    fun getPurchasePlace(message: String): String
    fun getPurchaseDate(message: String): Calendar?
    fun getPurchaseCardNumber(message: String): String
    fun getPurchaseCardType(message: String): String
}