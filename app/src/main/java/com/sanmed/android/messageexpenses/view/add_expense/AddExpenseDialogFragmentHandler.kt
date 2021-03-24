package com.sanmed.android.messageexpenses.view.add_expense

import androidx.fragment.app.Fragment
import com.sanmed.android.messageexpenses.view.dialog.DialogFragmentHandler

class AddExpenseDialogFragmentHandler(fragment: Fragment, private val viewModel:IAddExpenseViewModel) : DialogFragmentHandler<IAddExpenseViewModel>(fragment,viewModel) {

    init {
        viewModel.getOnDisMiss().observe(fragment.viewLifecycleOwner,this::onDismiss)
    }

    private fun onDismiss(dismiss: Boolean) {
        if(dismiss){
            disMiss()
            viewModel.getOnDisMissCompleted()
        }
    }
}