package com.sanmed.android.messageexpenses.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Expense(
    @PrimaryKey
    val id:String,
    val place:String,
    val price: Float,
    val date :Date)
    {

}