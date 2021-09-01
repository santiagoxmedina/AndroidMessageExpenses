package com.sanmed.android.messageexpenses.view

import java.math.BigDecimal

interface ICategoryExpenseView {
    fun  getAmount(): BigDecimal
    fun  getAmountString():String
    fun  getName():String
    fun  getPercentage():Float
    fun  getPercentageString():String
    fun  getType():Int
    fun  getId():String
    fun setName(name: String)
    fun setAmount(amount: BigDecimal)
}