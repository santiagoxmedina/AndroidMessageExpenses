package com.sanmed.android.messageexpenses.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class CategoryExpenseEntity(
    @PrimaryKey val id:String,
    @ColumnInfo(name = "name")val name:String,
    @ColumnInfo(name = "amount")val amount: Float)