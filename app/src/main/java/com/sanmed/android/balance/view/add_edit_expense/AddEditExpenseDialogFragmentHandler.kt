package com.sanmed.android.balance.view.add_edit_expense

import androidx.fragment.app.Fragment
import com.sanmed.android.balance.view.dialog.DialogFragmentHandler
import com.sanmed.android.balance.viewmodel.add_edit_expense.IAddEditExpenseViewModel

class AddEditExpenseDialogFragmentHandler<T>(
    fragment: Fragment,
    private val viewModel: IAddEditExpenseViewModel<T>
) : DialogFragmentHandler<IAddEditExpenseViewModel<T>>(fragment, viewModel) {

    init {
        viewModel.getOnDisMiss().observe(fragment.viewLifecycleOwner, this::onDismiss)
    }

    private fun onDismiss(dismiss: Boolean) {
        if (dismiss) {
            disMiss()
            viewModel.getOnDisMissCompleted()
        }
    }
}