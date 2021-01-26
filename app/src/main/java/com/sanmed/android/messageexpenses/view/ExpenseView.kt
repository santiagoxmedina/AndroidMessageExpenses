package com.sanmed.android.messageexpenses.view

import java.text.NumberFormat

class ExpenseView(private val _amount: Int,private val _name: String,private val _percentage: Float,private val _type: Int,private val _id: Int):IExpense {

    companion object{
        private  val percentageFormat = NumberFormat.getPercentInstance()
        private  val currencyFormat = NumberFormat.getCurrencyInstance()
    }

    override fun getAmount(): Int {
        return _amount
    }

    override fun getAmountString(): String {
        return currencyFormat.format(_amount)
    }

    override fun getName(): String {
        return _name
    }

    override fun getPercentage(): Float {
        return _percentage
    }

    override fun getPercentageString(): String {
        return percentageFormat.format(getPercentage())
    }

    override fun getType(): Int {
        return  _type
    }

    override fun getId(): Int {
        return  _id
    }
}