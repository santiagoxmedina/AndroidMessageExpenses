package com.sanmed.android.messageexpenses.view.utilities

import java.text.NumberFormat

object CurrencyUtilities {
          private val currencyFormat = NumberFormat.getCurrencyInstance()

        fun format(amount:Float?):String{
            return currencyFormat.format(amount)
        }

}