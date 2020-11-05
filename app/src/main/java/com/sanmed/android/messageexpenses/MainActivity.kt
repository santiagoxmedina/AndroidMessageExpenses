package com.sanmed.android.messageexpenses

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.sanmed.android.messageexpenses.entities.Expense
import com.sanmed.android.messageexpenses.presenter.ExpensesViewModel
import com.sanmed.android.messageexpenses.utilities.ExpensesTextUtility


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewadapter: ExpensesAdapter
    private lateinit var model: ExpensesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewadapter = ExpensesAdapter()
        list_view_expenses.adapter = viewadapter
        subscribeToViewModel()
    }

    private fun subscribeToViewModel(){
        model = ViewModelProviders.of(this)[ExpensesViewModel::class.java]
        val expensesObserver = androidx.lifecycle.Observer<List<Expense>>{
            updateExpenses(it as MutableList<Expense>)
        }
        model.expenses.observe(this,expensesObserver)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btnLoadSMS) {

            if (ContextCompat.checkSelfPermission(
                    baseContext,
                    "android.permission.READ_SMS"
                ) == PackageManager.PERMISSION_GRANTED
            ) {
               // val expenses = loadExpenses()
                //UpdateExpenses(expenses)

            } else {
                ActivityCompat.requestPermissions(this, arrayOf("android.permission.READ_SMS"), 123)
            }
        }
    }

    private fun updateExpenses(expenses: MutableList<Expense>){
        viewadapter.expenses = expenses
    }

    private fun loadExpenses(): MutableList<Expense> {
        val smsExpenses = mutableListOf<Expense>()
        contentResolver?.query(Uri.parse("content://sms/inbox"), null, null, null, null)?.use {
            var msgData: String
            it.moveToFirst()
            do {
                msgData = it.getString(it.getColumnIndex("body"))
                if (msgData.contains("Bancolombia le informa Compra por")) {
                    val place = ExpensesTextUtility.getPurchasePlace(msgData)
                    val price = ExpensesTextUtility.getPurchasePrice(msgData)
                    val date = ExpensesTextUtility.getPurchaseDate(msgData)
                    val id =  date.toString()+price
                    val expense = Expense(id,place,price,date)

                    smsExpenses.add(expense)
                }
            } while (it.moveToNext())
        }
       return smsExpenses
    }
}
