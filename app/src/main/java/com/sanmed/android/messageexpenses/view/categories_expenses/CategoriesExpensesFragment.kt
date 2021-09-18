package com.sanmed.android.messageexpenses.view.categories_expenses

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentCategoriesExpensesBinding
import com.sanmed.android.messageexpenses.model.action.IAction
import com.sanmed.android.messageexpenses.model.helpers.DialogHelper
import com.sanmed.android.messageexpenses.view.diff.DiffExpense
import com.sanmed.android.messageexpenses.view.add_category_expense.AddCategoryExpenseDialogFragmentHandler
import com.sanmed.android.messageexpenses.viewmodel.categories_expenses.CategoriesExpensesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesExpensesFragment : Fragment() {
    val viewModel  by viewModels<CategoriesExpensesViewModel>()
    private lateinit var binding : FragmentCategoriesExpensesBinding
    private lateinit var adapterCategory : CategoryExpensesAdapter
    private lateinit var categoryCategoryDialog:AddCategoryExpenseDialogFragmentHandler


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
        adapterCategory = CategoryExpensesAdapter( DiffExpense(),getOnEditCategoryAction())
        binding.listExpensesRecyclerView.adapter = adapterCategory
        categoryCategoryDialog = AddCategoryExpenseDialogFragmentHandler(this,viewModel.getAddExpenseViewModel())
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
            viewModel.getAddExpenseViewModel().onAddCategoryExpenseCompleted()
        }
    }

    private fun showCategoryExpenseDialog() {
        DialogHelper.showCategoryExpenseDialog(categoryCategoryDialog,this)
    }

    private fun onExpensesChanged(expens:List<ICategoryExpenseView?>) {
        adapterCategory.submitList(expens)
    }

}