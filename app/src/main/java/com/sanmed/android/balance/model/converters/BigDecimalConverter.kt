package com.sanmed.android.balance.model.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverter {
    @TypeConverter
    fun fromString(value: String?): BigDecimal? {
        return value?.toBigDecimal()
    }

    @TypeConverter
    fun toString(value: BigDecimal?): String? {
        return value?.toString()
    }
}