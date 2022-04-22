package com.sanmed.android.balance.view.categories_expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanmed.android.balance.R
import com.sanmed.android.balance.databinding.FragmentCategoriesExpensesBinding
import com.sanmed.android.balance.model.action.IAction
import com.sanmed.android.balance.model.helpers.DialogHelper
import com.sanmed.android.balance.view.add_edit_expense.AddEditExpenseDialogFragmentHandler
import com.sanmed.android.balance.view.diff.DiffCategoryExpenseView
import com.sanmed.android.balance.viewmodel.categories_expenses.CategoriesExpensesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesExpensesFragment : Fragment() {
    val viewModel  by viewModels<CategoriesExpensesViewModel>()
    private lateinit var binding : FragmentCategoriesExpensesBinding
    private lateinit var adapterCategory : CategoryExpensesAdapter
    private lateinit var categoryCategoryDialog:AddEditExpenseDialogFragmentHandler<ICategoryExpenseView>


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
        adapterCategory = CategoryExpensesAdapter( DiffCategoryExpenseView(),getOnEditCategoryAction())
        binding.listExpensesRecyclerView.adapter = adapterCategory
        categoryCategoryDialog = AddEditExpenseDialogFragmentHandler(this,viewModel.getAddExpenseViewModel())
        initSubscribers();
    }

    private fun getOnEditCategoryAction(): IAction<ICategoryExpenseView,View> {
        return  object: IAction<ICategoryExpenseView,View>{
            override fun onAction(categoryExpenseView: ICategoryExpenseView, view: View) {
                val popup = PopupMenu(requireContext(), view)
                val inflater: MenuInflater = popup.menuInflater
                inflater.inflate(R.menu.options_menu, popup.menu)
                popup.setOnMenuItemClickListener (getMenuListener(categoryExpenseView))
                popup.show()
            }
        }
    }

    private fun getMenuListener(categoryExpenseView: ICategoryExpenseView): PopupMenu.OnMenuItemClickListener? {
        return PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit -> {
                    viewModel.onEdit(categoryExpenseView)
                    true
                }
                R.id.delete -> {
                    viewModel.onDelete(categoryExpenseView)
                    true
                }
                else -> false
            }
        }
    }

    private fun initSubscribers() {
        viewModel.categoriesExpenses.observe(viewLifecycleOwner,this::onExpensesChanged)
        viewModel.getAddExpenseViewModel().getShowDialog().observe(viewLifecycleOwner,this::onShowCategoryExpenseDialog)
    }

    private fun onShowCategoryExpenseDialog(addExpense : Boolean?) {
        if(addExpense == true) {
            showCategoryExpenseDialog();
            viewModel.getAddExpenseViewModel().onAddExpenseCompleted()
        }
    }

    private fun showCategoryExpenseDialog() {
        DialogHelper.showExpenseDialog(categoryCategoryDialog,this)
    }

    private fun onExpensesChanged(expens:List<ICategoryExpenseView?>) {
        adapterCategory.submitList(expens)
    }

}