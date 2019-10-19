package com.sanmed.android.messageexpenses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoadSMS.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btnLoadSMS){
            Log.v("test","button clic");
        }
    }
}
