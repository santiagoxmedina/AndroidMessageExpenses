package com.sanmed.android.messageexpenses.model.repository

import com.sanmed.android.messageexpenses.model.entities.Expense

interface SMSReader {
    fun getExpenses(): Resource<List<Expense>>
}