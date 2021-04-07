package com.sanmed.android.messageexpenses.model.repository

import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity

interface SMSReader {
    fun getExpenses(): Resource<List<CategoryExpenseEntity>>
}