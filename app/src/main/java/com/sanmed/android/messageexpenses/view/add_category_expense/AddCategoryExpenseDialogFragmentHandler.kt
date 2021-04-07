package com.sanmed.android.messageexpenses.view.add_category_expense

import androidx.fragment.app.Fragment
import com.sanmed.android.messageexpenses.view.dialog.DialogFragmentHandler

class AddCategoryExpenseDialogFragmentHandler(fragment: Fragment, private val viewModel:IAddCategoryExpenseViewModel) : DialogFragmentHandler<IAddCategoryExpenseViewModel>(fragment,viewModel) {

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