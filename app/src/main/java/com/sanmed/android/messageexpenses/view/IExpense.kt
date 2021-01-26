package com.sanmed.android.messageexpenses.view

interface IExpense {
    fun  getAmount():Int
    fun  getAmountString():String
    fun  getName():String
    fun  getPercentage():Float
    fun  getPercentageString():String
    fun  getType():Int
    fun  getId():Int
}