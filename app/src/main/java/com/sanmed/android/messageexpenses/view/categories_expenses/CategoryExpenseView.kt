package com.sanmed.android.messageexpenses.view.categories_expenses

import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import com.sanmed.android.messageexpenses.view.utilities.CurrencyUtilities
import java.math.BigDecimal
import java.text.NumberFormat

class CategoryExpenseView(private val _id: String,private var _name: String,private var _amount: BigDecimal,private val _percentage: Float, private val _type: Int ):
    ICategoryExpenseView {

    private  val percentageFormat = NumberFormat.getPercentInstance()

    override fun getAmount(): BigDecimal {
        return _amount
    }

    override fun getAmountString() = CurrencyUtilities.format(_amount)

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

    override fun setAmount(amount: BigDecimal) {
        _amount = amount
    }
}