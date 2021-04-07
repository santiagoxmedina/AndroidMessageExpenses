package com.sanmed.android.messageexpenses.view.categories_expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentCategoriesExpensesBinding
import com.sanmed.android.messageexpenses.model.helpers.DialogHelper
import com.sanmed.android.messageexpenses.view.DiffExpense
import com.sanmed.android.messageexpenses.view.ExpensesAdapter
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import com.sanmed.android.messageexpenses.view.add_category_expense.AddCategoryExpenseDialogFragmentHandler
import com.sanmed.android.messageexpenses.viewmodel.categories_expenses.CategoriesExpensesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesExpensesFragment : Fragment() {
    val viewModel  by viewModels<CategoriesExpensesViewModel>()
    private lateinit var binding : FragmentCategoriesExpensesBinding
    private lateinit var adapter : ExpensesAdapter
    private lateinit var addCategoryDialog:AddCategoryExpenseDialogFragmentHandler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories_expenses,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExpensesAdapter( DiffExpense())
        binding.listExpensesRecyclerView.adapter = adapter
        addCategoryDialog = AddCategoryExpenseDialogFragmentHandler(this,viewModel.getAddExpenseViewModel())
        initSubscribers();
    }

    private fun initSubscribers() {
        viewModel.categoriesExpenses.observe(viewLifecycleOwner,this::onExpensesChanged)
        viewModel.addExpense.observe(viewLifecycleOwner,this::onAddExpense)
    }

    private fun onAddExpense(addExpense : Boolean) {
        if(addExpense) {
            showAddExpenseDialog();
            viewModel.onAddExpenseCompleted()
        }
    }

    private fun showAddExpenseDialog() {
        DialogHelper.showAddExpenseDialog(addCategoryDialog)
    }

    private fun onExpensesChanged(expens:List<ICategoryExpenseView?>) {
        adapter.submitList(expens)
    }
}