package com.sanmed.android.messageexpenses

import com.sanmed.android.messageexpenses.utilities.ExpensesTextUtility
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ExpensesTextUtilityTest {

    var purchaseDebitMessage:String = "Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 12:59. 22/10/2019 T.Deb *0214. Inquietudes al 0345109095/018000931987.";
    var purchaseCreditMessage:String = "Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 12:59. 22/10/2019 T.Cred *0214. Inquietudes al 0345109095/018000931987.";
    var purchaseDateUnder12Message:String = "Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 8:59. 22/10/2019 T.Cred *0214. Inquietudes al 0345109095/018000931987.";
    var purchaseDateSame12tMessage:String = "Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 12:59. 22/10/2019 T.Cred *0214. Inquietudes al 0345109095/018000931987.";
    var purchaseDateOver12Message:String = "Bancolombia le informa Compra por \$11.390,00 en TERPEL MED PILARICA 15:59. 2/10/2019 T.Cred *0214. Inquietudes al 0345109095/018000931987.";

    @Test
    fun getPurchasePrice() {

        assertEquals(
            "Purchase prices are not equal",
            11390f,
            ExpensesTextUtility.getPurchasePrice(purchaseDebitMessage)
        )
    }

    @Test
    fun getPurchasePlace() {

        assertEquals(
            "Purchase Place are not equal",
            "TERPEL MED PILARICA",
            ExpensesTextUtility.getPurchasePlace(purchaseDebitMessage)
        )
    }

    @Test
    fun getPurchaseDateUnder12() {
        val  cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2019)
        cal.set(Calendar.MONTH, Calendar.OCTOBER)
        cal.set(Calendar.DAY_OF_MONTH, 22)
        cal.set(Calendar.HOUR_OF_DAY, 8)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 0)
        assertEquals("Purchase Date are not equal", cal.time.toString(), ExpensesTextUtility.getPurchaseDate(purchaseDateUnder12Message).toString())
    }

    @Test
    fun getPurchaseDateOver12() {
        val  cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2019)
        cal.set(Calendar.MONTH, Calendar.OCTOBER)
        cal.set(Calendar.DAY_OF_MONTH, 22)
        cal.set(Calendar.HOUR_OF_DAY, 15)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 0)
        assertEquals("Purchase Date are not equal", cal.time.toString(), ExpensesTextUtility.getPurchaseDate(purchaseDateOver12Message).toString())
    }

    @Test
    fun getPurchaseDateSame12() {
        val  cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2019)
        cal.set(Calendar.MONTH, Calendar.OCTOBER)
        cal.set(Calendar.DAY_OF_MONTH, 22)
        cal.set(Calendar.HOUR_OF_DAY, 12)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 0)
        assertEquals("Purchase Date are not equal", cal.time.toString(), ExpensesTextUtility.getPurchaseDate(purchaseDateSame12tMessage).toString())
    }

    @Test
    fun getPurchaseCardNumber() {
        assertEquals(
            "Purchase Date are not equal","*0214",
            ExpensesTextUtility.getPurchaseCardNumber(purchaseDebitMessage)
        )
    }

    @Test
    fun getPurchaseCardTypeDebit() {
        assertEquals(
            "Purchase Date are not equal","T.Deb",
            ExpensesTextUtility.getPurchaseCardType(purchaseDebitMessage)
        )
    }

    @Test
    fun getPurchaseCardTypeCredit() {
        assertEquals(
            "Purchase Date are not equal","T.Cred",
            ExpensesTextUtility.getPurchaseCardType(purchaseCreditMessage)
        )
    }
}