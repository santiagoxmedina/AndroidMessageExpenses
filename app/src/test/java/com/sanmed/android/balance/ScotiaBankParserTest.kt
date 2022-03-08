package com.sanmed.android.balance

import com.sanmed.android.balance.view.utilities.ExpensesTextUtility
import org.junit.Assert
import org.junit.Test

class ScotiaBankParserTest {
    var purchaseScotiaBank1 = "Scotiabank Colpatria: Realizaste trans o compra recurrente en PAYU IFOOD por 101,300 con tu tarjeta PLATINUM LIFEMILES. L?nea al 4232230 o 0"
    var purchaseScotiaBank2 = "Scotiabank Colpatria: Realizaste trans o compra recurrente en PAY WPLAY.CO por 63,000 con tu tarjeta PLATINUM LIFEMILES. L?nea al 4232230 o "
    @Test
    fun getPurchasePriceScotiaBank() {

        Assert.assertEquals(
            "Purchase prices are not equal",
            "101300".toBigDecimal(),
            ExpensesTextUtility.getPurchasePrice(purchaseScotiaBank1)
        )
        Assert.assertEquals(
            "Purchase prices are not equal",
            "63000".toBigDecimal(),
            ExpensesTextUtility.getPurchasePrice(purchaseScotiaBank2)
        )
    }

    @Test
    fun getPurchasePlaceScotiaBank() {

        Assert.assertEquals(
            "Purchase Place are not equal",
            "PAYU IFOOD",
            ExpensesTextUtility.getPurchasePlace(purchaseScotiaBank1)
        )
    }
}