package com.sanmed.android.messageexpenses.model.expensess_parsers

import java.util.*

interface IExpensesParser {
    fun getPurchasePrice(message: String): Float
    fun getPurchasePlace(message: String): String
    fun getPurchaseDate(message: String): Date
    fun getPurchaseCardNumber(message: String): String
    fun getPurchaseCardType(message: String): String
}