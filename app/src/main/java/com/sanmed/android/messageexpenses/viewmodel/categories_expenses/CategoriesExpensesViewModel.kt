package com.sanmed.android.messageexpenses.viewmodel.categories_expenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.model.repository.ICategoryExpensesRepository
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import com.sanmed.android.messageexpenses.view.add_category_expense.IAddCategoryExpenseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesExpensesViewModel @Inject constructor(private val _addExpenseViewModel:IAddCategoryExpenseViewModel ,private val categoryExpensesRepository: ICategoryExpensesRepository):ViewModel() {

    val categoriesExpenses: LiveData<List<ICategoryExpenseView?>>
    get() = categoryExpensesRepository.getCategoriesExpenses()

    private val _addExpense = MutableLiveData<Boolean>()
    val addExpense: LiveData<Boolean>
        get() = _addExpense

    fun onAddExpense(){
        _addExpense.value = true
    }

    fun onAddExpenseCompleted(){
        _addExpense.value = false
    }

    fun getAddExpenseViewModel(): IAddCategoryExpenseViewModel {
        return _addExpenseViewModel
    }

}