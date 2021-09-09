package com.sanmed.android.messageexpenses.model.parsers

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class BancolombiaParser : IExpensesParser {

    //"Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 12:59. 22/10/2019 T.Deb *0214. Inquietudes al 0345109095/018000931987.";

    override fun getPurchasePrice(message: String): BigDecimal {
        val regexValue = "\\$[\\d.,']+".toRegex()
        var match = regexValue.find(message)
        var result: String? = match?.value
        result = result?.replace("$", "")
        result = result?.replace(".", "")
        result = result?.replace(",", ".")
        return result?.toBigDecimal() ?: BigDecimal.ZERO
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

    override fun getPurchaseDate(message: String):Calendar? {
        val regexValue = " \\d{1,2}:\\d{1,2}. \\d{1,2}/\\d{1,2}/\\d{1,4} ".toRegex();
        var match = regexValue.find(message)
        var result: String
        result = match?.value ?: ""
        result = result.replace(" ", "")
        result = result.replace(".", " ")

        val format = SimpleDateFormat("H:mm dd/MM/yyyy",Locale.getDefault())
        var calendar:Calendar? = null
        format.parse(result)?.let {
            calendar = Calendar.getInstance()
            calendar?.time =  it
        }
        return calendar
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