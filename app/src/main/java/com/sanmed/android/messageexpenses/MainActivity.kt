package com.sanmed.android.messageexpenses

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.text.method.ScrollingMovementMethod
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.android.messageexpenses.entities.Expense
import kotlinx.android.synthetic.main.expenseitem.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewadapter: ExpensesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoadSMS.setOnClickListener(this)
        viewadapter = ExpensesAdapter()
        list_view_expenses.adapter = viewadapter
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        list_view_expenses.setHasFixedSize(true)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btnLoadSMS) {

            if (ContextCompat.checkSelfPermission(
                    baseContext,
                    "android.permission.READ_SMS"
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                loadSMS()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf("android.permission.READ_SMS"), 123)
            }


        }
    }

    private fun loadSMS() {
        val expenses = mutableListOf<Expense>()
        contentResolver?.query(Uri.parse("content://sms/inbox"), null, null, null, null)?.use {
            var msgData: String
            it.moveToFirst()
            do {
                msgData = it.getString(it.getColumnIndex("body"))
                if (msgData.contains("Bancolombia le informa Compra por")) {
                    val expense = Expense()
                    expense.place = getPurchasePlace(msgData)
                    expense.price = getPurchasePrice(msgData)
                    expense.date = getPurchaseDate(msgData)
                    expenses.add(expense)
                }
            } while (it.moveToNext())
        }
        viewadapter.expenses = expenses
    }

    private fun getPurchaseDetails(message: String): String {
        var result = ""
        result += "\nDetails:"
        result += String.format("\n Lugar: %s", getPurchasePlace(message))
        result += String.format("\n Valor: %s", getPurchasePrice(message))
        result += String.format("\n Fecha: %s", getPurchaseDate(message).toString())
        result += String.format("\n Tipo de tarjeta: %s", getPurchaseCardType(message))
        result += String.format("\n Numero de tarjeta: %s", getPurchaseCardNumber(message))
        return result
    }


    companion object {
        @JvmStatic
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
