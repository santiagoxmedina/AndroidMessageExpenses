package com.sanmed.android.messageexpenses.model.expensess_parsers

import java.text.SimpleDateFormat
import java.util.*

class ScotiaBankParser:EmptyParser() {
    //"Scotiabank Colpatria: Realizaste trans o compra recurrente en PAYU IFOOD por 101,300 con tu tarjeta PLATINUM LIFEMILES. L?nea al 4232230 o 0"
    override fun getPurchasePrice(message: String): Float {
        val regexValue = "\\d[\\d.,']+".toRegex()
        var match = regexValue.find(message)
        var result: String? = match?.value
        result = result?.replace(".", "")
        result = result?.replace(",", "")
        return result?.toFloat() ?: 0f
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