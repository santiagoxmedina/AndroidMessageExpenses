package com.sanmed.android.messageexpenses.view.dialog

import android.app.Dialog
import android.content.Context
import com.sanmed.android.messageexpenses.view.add_expense.IAddExpenseView

interface IDialogHandler <T> {
    fun getContext(): Context
    fun getDialog(): Dialog?
    fun setDialog(dialog: Dialog)
    fun getViewModel(): T
}