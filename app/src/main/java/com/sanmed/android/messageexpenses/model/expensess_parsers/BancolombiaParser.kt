package com.sanmed.android.messageexpenses.model.expensess_parsers

import java.text.SimpleDateFormat
import java.util.*

class BancolombiaParser : IExpensesParser {

    //"Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 12:59. 22/10/2019 T.Deb *0214. Inquietudes al 0345109095/018000931987.";

    override fun getPurchasePrice(message: String): Float {
        val regexValue = "\\$[\\d.,']+".toRegex()
        var match = regexValue.find(message)
        var result: String? = match?.value
        result = result?.replace("$", "")
        result = result?.replace(".", "")
        result = result?.replace(",", ".")
        return result?.toFloat() ?: 0f
    }

    override fun getPurchasePlace(message: String): String {

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

    override fun getPurchaseDate(message: String): Date {
        val regexValue = " \\d{1,2}:\\d{1,2}. \\d{1,2}/\\d{1,2}/\\d{1,4} ".toRegex();
        var match = regexValue.find(message)
        var result: String
        result = match?.value ?: ""
        result = result.replace(" ", "")
        result = result.replace(".", " ")

        val format = SimpleDateFormat("H:mm dd/MM/yyyy")
        return format.parse(result)
    }

    override fun getPurchaseCardNumber(message: String): String {
        val regexValue = "\\*\\d{4,}".toRegex()
        var match = regexValue.find(message)
        return match?.value ?: "*0000"
    }

    override fun getPurchaseCardType(message: String): String {
        val regexValue = "T\\.\\w{1,5}".toRegex()
        var match = regexValue.find(message)
        return match?.value ?: "None"
    }
}