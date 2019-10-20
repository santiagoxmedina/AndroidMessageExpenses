package com.sanmed.android.messageexpenses

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoadSMS.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btnLoadSMS){

            if(ContextCompat.checkSelfPermission(baseContext, "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
                loadSMS()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf("android.permission.READ_SMS"), 123)
            }



        }
    }
    fun loadSMS(){
        smsTexts.text = "No SMS"
        contentResolver?.query(Uri.parse("content://sms/inbox"), null, null, null, null)?.use {
            if (it.moveToFirst()) { // must check the result to prevent exception
                do {
                    var msgData = ""
                    for (idx in 0 until it.columnCount) {
                        msgData += " " + it.getColumnName(idx) + ":" + it.getString(idx)
                    }
                    // use msgData
                    smsTexts.text = msgData
                } while (it.moveToNext())
            }
        }
    }
}
