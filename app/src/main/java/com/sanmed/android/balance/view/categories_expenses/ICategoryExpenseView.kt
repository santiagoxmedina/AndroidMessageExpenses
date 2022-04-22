package com.sanmed.android.balance.view.categories_expenses

import com.sanmed.android.balance.view.expense.ExpenseView
import com.sanmed.android.balance.view.expense.IExpenseView
import java.math.BigDecimal

interface ICategoryExpenseView: IExpenseView {
    fun  getAmountString():String
    fun  getPercentage():Float
    fun  getPercentageString():String
    fun  getType():Int
}