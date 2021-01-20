package com.sanmed.android.messageexpenses.view.utilities

import java.text.SimpleDateFormat
import java.util.*

class ExpensesTextUtility {
    companion object {

        fun getPurchasePrice(message: String): Float {
            val regexValue = "\\$[\\d.,']+".toRegex()
            var match = regexValue.find(message)
            var result: String? = match?.value
            result = result?.replace("$", "")
            result = result?.replace(".", "")
            result = result?.replace(",", ".")
            return result?.toFloat() ?: 0f
        }

        fun getPurchasePlace(message: String): String {

            val regexValue = "en .+ \\d\\d:".toRegex()
            var match = regexValue.find(message)
            var result: String
            result = match?.value ?: "(Ninguno)"
            val startRemoveRegexValue = "en\\h".toRegex()
            result = startRemoveRegexValue.replace(result, "")

            val timeRemoveRegexValue = "\\h\\d\\d:".toRegex()
            result = timeRemoveRegexValue.replace(result, "")
            return result
        }

        fun getPurchaseDate(message: String): Date {
            val regexValue = " \\d{1,2}:\\d{1,2}. \\d{1,2}/\\d{1,2}/\\d{1,4} ".toRegex();
            var match = regexValue.find(message)
            var result: String
            result = match?.value ?: ""
            result = result.replace(" ", "")
            result = result.replace(".", " ")

            val format = SimpleDateFormat("H:mm dd/MM/yyyy")
            return format.parse(result)
        }

        fun getPurchaseCardNumber(message: String): String {
            val regexValue = "\\*\\d{4,}".toRegex()
            var match = regexValue.find(message)
            return match?.value ?: "*0000"
        }

        fun getPurchaseCardType(message: String): String {
            val regexValue = "T\\.\\w{1,5}".toRegex()
            var match = regexValue.find(message)
            return match?.value ?: "None"
        }

    }
}