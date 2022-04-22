package com.sanmed.android.balance.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity
data class ExpenseEntity(
    @PrimaryKey val id:String,
    @ColumnInfo(name = "name")val name:String,
    @ColumnInfo(name = "amount")val amount: BigDecimal,
    @ColumnInfo(name = "date")val date: Calendar
)
