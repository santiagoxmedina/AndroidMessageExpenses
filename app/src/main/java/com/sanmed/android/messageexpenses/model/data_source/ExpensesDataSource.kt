package com.sanmed.android.messageexpenses.model.data_source

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.android.messageexpenses.model.helpers.SummaryItemHelper
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExpensesDataSource @Inject constructor(@ApplicationContext private val context: Context) :
    IExpensesDataSource {
    val expenses = MutableLiveData<List<ISummaryItemView>>()
    val _expenses: LiveData<List<ISummaryItemView>> get() = expenses
    override fun getSummaryExpenses(): LiveData<List<ISummaryItemView>> {
        return _expenses
    }

    override fun updateSummaryExpenses() {
        val cursor: Cursor? =
            context.contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)
        val result = mutableListOf<ISummaryItemView>()
        cursor?.apply {
            if (moveToFirst()) {
                do {
                    var msgData = ""
                    for (idx in 0 until columnCount) {
                        var summaryItemView: SummaryItemView? = SummaryItemHelper.parseFromSMS(
                            getString(
                                idx
                            )
                        )
                        summaryItemView?.let {
                            result.add(it)
                        }
                    }
                    // use msgData
                } while (moveToNext())
            }
        }?.close()
        expenses.postValue(result)
    }

    override fun onGroupByMonth() {
        TODO("Not yet implemented")
    }
}