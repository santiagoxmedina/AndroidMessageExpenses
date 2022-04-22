package com.sanmed.android.balance.view.budget


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanmed.android.balance.databinding.FragmentBudgetBinding
import com.sanmed.android.balance.model.action.IAction
import com.sanmed.android.balance.model.helpers.DialogHelper
import com.sanmed.android.balance.view.add_edit_expense.AddEditExpenseDialogFragmentHandler
import com.sanmed.android.balance.view.expense.ExpenseView
import com.sanmed.android.balance.view.expense.ExpensesAdapter
import com.sanmed.android.balance.viewmodel.budget.BudgetViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

@AndroidEntryPoint
class BudgetFragment : Fragment() {

    private lateinit var binding: FragmentBudgetBinding
    val viewModel by viewModels<BudgetViewModel>()
    private lateinit var expensesAdapter: ExpensesAdapter
    private lateinit var expenseDialog: AddEditExpenseDialogFragmentHandler<ExpenseView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBudgetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewComponents()
        initSubscribers()
        viewModel.init()
    }

    private fun initViewComponents() {
        expenseDialog =
            AddEditExpenseDialogFragmentHandler(this, viewModel.getAddExpenseViewModel())
        expensesAdapter = ExpensesAdapter(DiffExpenseView(), onEditExpense())
        binding.expenses.itemAnimator = SlideInUpAnimator()
        binding.expenses.adapter = expensesAdapter
        binding.addExpense.setOnClickListener { viewModel.addExpense() }
    }

    private fun initSubscribers() {
        viewModel.getExpenses().observe(viewLifecycleOwner, this::onExpensesUpdated)
        viewModel.getAvailableAmount().observe(viewLifecycleOwner, this::onAvailableAmountUpdated)
        viewModel.getTotalAmount().observe(viewLifecycleOwner, this::onTotalAmountUpdated)
        viewModel.getAddExpenseViewModel().getShowDialog()
            .observe(viewLifecycleOwner, this::onShowExpenseDialog)
    }

    private fun onShowExpenseDialog(addExpense: Boolean?) {
        if (addExpense == true) {
            showExpenseDialog();
            viewModel.getAddExpenseViewModel().onAddExpenseCompleted()
        }
    }

    private fun showExpenseDialog() {
        DialogHelper.showExpenseDialog(expenseDialog, this)
    }

    private fun onAvailableAmountUpdated(amount: String?) {
        binding.amount.text = amount
    }

    private fun onTotalAmountUpdated(amount: String?) {
        binding.totalAmount.text = amount
    }

    private fun onExpensesUpdated(expenses: List<ExpenseView>?) {
        expensesAdapter.submitList(expenses?.map {
            it.copy()
        })
    }

    private fun onEditExpense() = object : IAction<String?, View?> {
        override fun onAction(x: String?, y: View?) {
            x?.let {
                viewModel.editExpense(x)
            }
        }

    }

}