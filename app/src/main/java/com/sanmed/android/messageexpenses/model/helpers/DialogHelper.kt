package com.sanmed.android.messageexpenses.model.helpers

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ViewAddExpenseBinding
import com.sanmed.android.messageexpenses.view.add_expense.IAddExpenseView
import com.sanmed.android.messageexpenses.view.dialog.IDialogHandler


class DialogHelper {

    companion object{
        fun <T : IAddExpenseView> showAddExpenseDialog(dialogHandler: IDialogHandler<T>){
            if(dialogHandler.getDialog() == null){
                val inflater: LayoutInflater = LayoutInflater.from(dialogHandler.getContext())
                val binding:ViewAddExpenseBinding = DataBindingUtil.inflate(inflater,R.layout.view_add_expense,null,false)
                binding.viewModel = dialogHandler.getViewModel()
                val dialog: Dialog = createDialog(dialogHandler.getContext(),binding.root)
                dialogHandler.setDialog(dialog)
            }
            if(dialogHandler.getDialog()!=null && !dialogHandler.getDialog()!!.isShowing){
                dialogHandler.getDialog()!!.show()
            }
        }

        private fun createDialog(context: Context,view: View): Dialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setView(view)
            return builder.create()
        }
    }
    }

