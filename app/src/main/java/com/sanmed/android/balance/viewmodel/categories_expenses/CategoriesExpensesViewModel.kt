package com.sanmed.android.balance.viewmodel.categories_expenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.balance.model.repository.ICategoryExpensesRepository
import com.sanmed.android.balance.viewmodel.add_edit_expense.IAddEditExpenseViewModel
import com.sanmed.android.balance.view.categories_expenses.ICategoryExpenseView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesExpensesViewModel @Inject constructor(private val _addExpenseViewModel: IAddEditExpenseViewModel<ICategoryExpenseView>, private val categoryExpensesRepository: ICategoryExpensesRepository):ViewModel() {

    val categoriesExpenses: LiveData<List<ICategoryExpenseView?>>
    get() = categoryExpensesRepository.getCategoriesExpenses()

    fun onAddExpense(){
        GlobalScope.launch {
            _addExpenseViewModel.onAddExpense()
        }
    }

    fun getAddExpenseViewModel(): IAddEditExpenseViewModel<ICategoryExpenseView> {
        return _addExpenseViewModel
    }

    fun onEdit(categoryExpenseView: ICategoryExpenseView){
        GlobalScope.launch {
            _addExpenseViewModel.onEditExpense(categoryExpenseView)
        }
    }

    fun onDelete(categoryExpenseView: ICategoryExpenseView){
        GlobalScope.launch {
            categoryExpensesRepository.delete(categoryExpenseView)
        }
    }

}