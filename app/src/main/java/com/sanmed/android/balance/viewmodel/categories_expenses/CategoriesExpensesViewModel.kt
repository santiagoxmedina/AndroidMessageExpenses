package com.sanmed.android.balance.viewmodel.categories_expenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.balance.model.repository.ICategoryExpensesRepository
import com.sanmed.android.balance.view.add_category_expense.IAddCategoryExpenseViewModel
import com.sanmed.android.balance.view.categories_expenses.ICategoryExpenseView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesExpensesViewModel @Inject constructor(private val _addExpenseViewModel:IAddCategoryExpenseViewModel ,private val categoryExpensesRepository: ICategoryExpensesRepository):ViewModel() {

    val categoriesExpenses: LiveData<List<ICategoryExpenseView?>>
    get() = categoryExpensesRepository.getCategoriesExpenses()

    fun onAddExpense(){
        GlobalScope.launch {
            _addExpenseViewModel.onAddCategoryExpense()
        }
    }

    fun getAddExpenseViewModel(): IAddCategoryExpenseViewModel {
        return _addExpenseViewModel
    }

    fun onEdit(categoryExpenseView: ICategoryExpenseView){
        GlobalScope.launch {
            _addExpenseViewModel.onEditCategoryExpense(categoryExpenseView)
        }
    }

    fun onDelete(categoryExpenseView: ICategoryExpenseView){
        GlobalScope.launch {
            categoryExpensesRepository.delete(categoryExpenseView)
        }
    }

}