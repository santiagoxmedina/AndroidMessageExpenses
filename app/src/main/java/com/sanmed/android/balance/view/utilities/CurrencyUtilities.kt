package com.sanmed.android.balance.view.utilities

import java.math.BigDecimal
import java.text.NumberFormat

object CurrencyUtilities {
          private val currencyFormat = NumberFormat.getCurrencyInstance()

        fun format(amount:BigDecimal?):String{
            return currencyFormat.format(amount)
        }

}