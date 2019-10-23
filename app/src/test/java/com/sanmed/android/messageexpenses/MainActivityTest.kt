package com.sanmed.android.messageexpenses

import org.junit.Assert.*
import org.junit.Test

class MainActivityTest {

    var purchaseMessage:String = "Bancolombia le informa Compra por \$11.390,00 en MED PILARICA 17:59. 22/10/2019 T.Deb *0214. Inquietudes al 0345109095/018000931987.";

    @Test
    fun getPurchasePrice() {

        assertEquals(
            "Purchase prices are not equal",
            11390f,
            MainActivity.getPurchasePrice(purchaseMessage)
        )
    }

    @Test
    fun getPurchasePlace() {

        assertEquals(
            "Purchase prices are not equal",
            "MED PILARICA",
            MainActivity.getPurchasePlace(purchaseMessage)
        )
    }
}