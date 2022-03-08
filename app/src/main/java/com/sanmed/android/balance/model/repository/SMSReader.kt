package com.sanmed.android.balance.model.repository

import com.sanmed.android.balance.model.entities.CategoryExpenseEntity

interface SMSReader {
    fun getExpenses(): Resource<List<CategoryExpenseEntity>>
}