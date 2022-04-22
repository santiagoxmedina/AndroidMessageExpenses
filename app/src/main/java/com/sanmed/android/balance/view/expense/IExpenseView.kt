package com.sanmed.android.balance.view.expense

import java.math.BigDecimal

interface IExpenseView {
    fun getName(): String
    fun getAmount(): BigDecimal
    fun getId(): String
    fun setName(name: String)
    fun setAmount(amount: BigDecimal)
}