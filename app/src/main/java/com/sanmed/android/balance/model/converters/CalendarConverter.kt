package com.sanmed.android.balance.model.converters

import androidx.room.TypeConverter
import com.sanmed.android.balance.model.helpers.toCalendar
import com.sanmed.android.balance.model.helpers.toStringCalendar
import java.math.BigDecimal
import java.util.*

class CalendarConverter {
    @TypeConverter
    fun fromString(value: String?): Calendar? {
        return value?.toCalendar()
    }

    @TypeConverter
    fun toString(value: Calendar?): String? {
        return value?.toStringCalendar()
    }
}