package com.sanmed.android.balance.model.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.sanmed.android.balance.model.db.CategoryExpenseDAO
import com.sanmed.android.balance.model.entities.CategoryExpenseEntity
import com.sanmed.android.balance.model.helpers.CategoryExpenseHelper
import com.sanmed.android.balance.view.categories_expenses.ICategoryExpenseView
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCategoryExpensesDataSource @Inject constructor(private val categoryExpensesDAO: CategoryExpenseDAO) :
    ILocalCategoryExpensesDataSource {
    private val allTeams: Flow<List<CategoryExpenseEntity>> = categoryExpensesDAO.getAll()

    override fun addCategory(categoryExpenseView: ICategoryExpenseView) {
        categoryExpensesDAO.save(
            CategoryExpenseHelper.getCategoryExpenseEntityFromCategoryExpenseView(
                categoryExpenseView
            )
        )
    }

    override fun getCategoriesExpenses(): LiveData<List<ICategoryExpenseView?>> {
        return Transformations.map(
            allTeams.asLiveData(),
            CategoryExpenseHelper::getListCategoryExpenseEntityFromListCategoryExpenseView
        )
    }

    override fun delete(categoryExpenseView: ICategoryExpenseView) {
        categoryExpensesDAO.delete(
            CategoryExpenseHelper.getCategoryExpenseEntityFromCategoryExpenseView(
                categoryExpenseView
            )
        )
    }

    override fun editCategory(categoryExpenseView: ICategoryExpenseView) {
        categoryExpensesDAO.update(
            CategoryExpenseHelper.getCategoryExpenseEntityFromCategoryExpenseView(
                categoryExpenseView
            )
        )
    }
}