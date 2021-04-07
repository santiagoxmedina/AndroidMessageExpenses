package com.sanmed.android.messageexpenses.view

interface ICategoryExpenseView {
    fun  getAmount():Float
    fun  getAmountString():String
    fun  getName():String
    fun  getPercentage():Float
    fun  getPercentageString():String
    fun  getType():Int
    fun  getId():String
}