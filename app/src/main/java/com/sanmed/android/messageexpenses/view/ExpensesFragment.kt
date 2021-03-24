package com.sanmed.android.messageexpenses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentExpensesBinding
import com.sanmed.android.messageexpenses.model.helpers.DialogHelper
import com.sanmed.android.messageexpenses.view.add_expense.AddExpenseDialogFragmentHandler
import com.sanmed.android.messageexpenses.view.dialog.DialogFragmentHandler
import com.sanmed.android.messageexpenses.viewmodel.ExpensesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpensesFragment : Fragment() {
    val viewModel  by viewModels<ExpensesViewModel>()
    private lateinit var binding : FragmentExpensesBinding
    private lateinit var adapter : ExpensesAdapter
    private lateinit var addDialog:AddExpenseDialogFragmentHandler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expenses,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExpensesAdapter( DiffExpense())
        binding.listExpensesRecyclerView.adapter = adapter
        addDialog = AddExpenseDialogFragmentHandler(this,viewModel.getAddExpenseViewModel())
        initSubscribers();
    }

    private fun initSubscribers() {
        viewModel.expenses.observe(viewLifecycleOwner,this::onExpensesChanged)
        viewModel.addExpense.observe(viewLifecycleOwner,this::onAddExpense)
    }

    private fun onAddExpense(addExpense : Boolean) {
        if(addExpense) {
            showAddExpenseDialog();
            viewModel.onAddExpenseCompleted()
        }
    }

    private fun showAddExpenseDialog() {
        DialogHelper.showAddExpenseDialog(addDialog)
    }

    private fun onExpensesChanged( expenses:List<IExpense?>) {
        adapter.submitList(expenses)
    }
}