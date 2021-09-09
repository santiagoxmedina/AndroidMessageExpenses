package com.sanmed.android.messageexpenses.model.parsers

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ScotiaBankParser:EmptyParser() {
    //"Scotiabank Colpatria: Realizaste trans o compra recurrente en PAYU IFOOD por 101,300 con tu tarjeta PLATINUM LIFEMILES. L?nea al 4232230 o 0"
    override fun getPurchasePrice(message: String): BigDecimal {
        val regexValue = "\\d[\\d.,']+".toRegex()
        var match = regexValue.find(message)
        var result: String? = match?.value
        result = result?.replace(".", "")
        result = result?.replace(",", "")
        return result?.toBigDecimal() ?: BigDecimal.ZERO
    }

    override fun getPurchasePlace(message: String): String {
        val regexValue = "en\\h.+\\hpor".toRegex()
        var match = regexValue.find(message)
        var result: String
        result = match?.value ?: "(Ninguno)"
        val startRemoveRegexValue = "en\\h".toRegex()
        result = startRemoveRegexValue.replace(result, "")

        val endRemoveRegexValue = "\\hpor".toRegex()
        result = endRemoveRegexValue.replace(result, "")
        return result
    }
}