package com.sanmed.android.messageexpenses.model.helpers

import java.util.*

class UUIDHelper {
    companion object{
        fun generate():String{
            var uniqueID = UUID.randomUUID().toString()
            return uniqueID.substring(8)
        }
    }
}