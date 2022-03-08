package com.sanmed.android.balance.view.utilities

import com.sanmed.android.balance.model.exeptions.MessageNotSupportedException
import com.sanmed.android.balance.model.parsers.BancolombiaParser
import com.sanmed.android.balance.model.parsers.IExpensesParser
import com.sanmed.android.balance.model.parsers.ScotiaBankParser
import java.math.BigDecimal
import java.util.*

class ExpensesTextUtility {
    companion object {
        private val bancolombiaParser: IExpensesParser = BancolombiaParser()
        private val scotiaBankParser: IExpensesParser = ScotiaBankParser()
        private val parsers: HashMap<String, IExpensesParser> = createParsers()

        private fun createParsers(): java.util.HashMap<String, IExpensesParser> {
            val result = HashMap<String, IExpensesParser>()
            result["Bancolombia le informa Compra por"] = bancolombiaParser
            result["Scotiabank Colpatria:"] = scotiaBankParser
            return result
        }

        private fun getParser(message: String): IExpensesParser {

            parsers.forEach {
                if (message.contains(it.key)) {
                    return it.value
                }
            }
            throw MessageNotSupportedException("Parser not found")
        }

        fun getPurchasePrice(message: String): BigDecimal {
            return getParser(message).getPurchasePrice(message)
        }

        fun getPurchasePlace(message: String): String {

            return getParser(message).getPurchasePlace(message)
        }

        fun getPurchaseDate(message: String): Calendar? {
            return getParser(message).getPurchaseDate(message)
        }

        fun getPurchaseCardNumber(message: String): String {
            return getParser(message).getPurchaseCardNumber(message)
        }

        fun getPurchaseCardType(message: String): String {
            return getParser(message).getPurchaseCardType(message)
        }

    }
}