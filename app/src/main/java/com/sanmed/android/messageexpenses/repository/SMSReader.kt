package com.sanmed.android.messageexpenses.repository

import com.sanmed.android.messageexpenses.entities.Expense

interface SMSReader {
    fun getExpenses(): Resource<List<Expense>>
}