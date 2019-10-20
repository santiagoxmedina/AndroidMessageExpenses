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


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoadSMS.setOnClickListener(this)
        smsTexts.movementMethod = ScrollingMovementMethod()
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
        smsTexts.text = "No SMS"
        contentResolver?.query(Uri.parse("content://sms/inbox"), null, null, null, null)?.use {
            var resultData = String.format("Total messages %d \n", it.count)
            resultData += "Gastos"
            var msgData: String
            it.moveToFirst()
            do {
                msgData = it.getString(it.getColumnIndex("body"))
                if (msgData.contains("Bancolombia le informa Compra por")) {
                    resultData += "\n\n\n"
                    resultData += msgData
                    resultData += getPurchaseDetails(msgData)
                }
            } while (it.moveToNext())
            smsTexts.text = resultData
        }
    }

    private fun getPurchaseDetails(message: String):String {
        val regexValue = "\\$[\\d.,']+".toRegex()
        val regexSite = " [\\w\\s]+ \\d{2}:\\d{2}".toRegex()

        var result = ""
        result +="\nDetails"
        var match = regexValue.find(message)
        result += String.format("\n Valor: %s",match?.value)
        match = regexSite.find(message)
        result += String.format("\n Lugar: %s",match?.value)
        return result
    }
}
