package com.sanmed.android.balance.view.expense

import java.math.BigDecimal
import java.util.*


data class ExpenseView(val id:String,var name:String, var amount:BigDecimal, var date: Calendar)
