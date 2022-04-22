package com.sanmed.android.balance.model.helpers

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.sanmed.android.balance.R
import com.sanmed.android.balance.databinding.ViewAddCategoryExpenseBinding
import com.sanmed.android.balance.viewmodel.expenseDialog.IExpenseDialogViewModel
import com.sanmed.android.balance.view.dialog.IDialogHandler


class DialogHelper {

    companion object {
        fun <T : IExpenseDialogViewModel> showExpenseDialog(
            dialogHandler: IDialogHandler<T>,
            lifecycleOwner: LifecycleOwner
        ) {
            if (dialogHandler.getDialog() == null) {
                val inflater: LayoutInflater = LayoutInflater.from(dialogHandler.getContext())
                val binding: ViewAddCategoryExpenseBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.view_add_category_expense, null, false
                )
                binding.viewModel = dialogHandler.getViewModel()
                binding.lifecycleOwner = lifecycleOwner
                val dialog: Dialog = createDialog(dialogHandler.getContext(), binding.root)
                dialogHandler.setDialog(dialog)
            }
            if (dialogHandler.getDialog() != null && !dialogHandler.getDialog()!!.isShowing) {
                dialogHandler.getDialog()!!.show()
            }
        }

        private fun createDialog(context: Context, view: View): Dialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setView(view)
            return builder.create()
        }
    }
}

