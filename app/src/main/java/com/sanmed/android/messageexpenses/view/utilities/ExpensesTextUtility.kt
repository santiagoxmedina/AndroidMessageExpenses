package com.sanmed.android.messageexpenses.view.utilities

import com.sanmed.android.messageexpenses.model.exeptions.MessageNotSupportedException
import com.sanmed.android.messageexpenses.model.expensess_parsers.BancolombiaParser
import com.sanmed.android.messageexpenses.model.expensess_parsers.EmptyParser
import com.sanmed.android.messageexpenses.model.expensess_parsers.IExpensesParser
import com.sanmed.android.messageexpenses.model.expensess_parsers.ScotiaBankParser
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

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
            throw MessageNotSupportedException()
        }

        fun getPurchasePrice(message: String): Float {
            return getParser(message).getPurchasePrice(message)
        }

        fun getPurchasePlace(message: String): String {

            return getParser(message).getPurchasePlace(message)
        }

        fun getPurchaseDate(message: String): Date {
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