package com.sanmed.android.messageexpenses.view.categories_expenses

import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import java.text.NumberFormat

class CategoryExpenseView(private val _id: String,private var _name: String,private var _amount: Float,private val _percentage: Float, private val _type: Int ):
    ICategoryExpenseView {

    companion object{
        private  val percentageFormat = NumberFormat.getPercentInstance()
        private  val currencyFormat = NumberFormat.getCurrencyInstance()
    }

    override fun getAmount(): Float {
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

    override fun getId(): String {
        return  _id
    }

    override fun setName(name: String) {
        _name = name
    }

    override fun setAmount(amount: Float) {
        _amount = amount
    }
}