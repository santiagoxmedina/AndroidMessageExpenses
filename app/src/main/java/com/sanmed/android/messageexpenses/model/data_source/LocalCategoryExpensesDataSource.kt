package com.sanmed.android.messageexpenses.model.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.sanmed.android.messageexpenses.model.db.CategoryExpenseDAO
import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity
import com.sanmed.android.messageexpenses.model.helpers.CategoryExpenseHelper
import com.sanmed.android.messageexpenses.view.ICategoryExpenseView
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCategoryExpensesDataSource @Inject constructor(private val categoryExpensesDAO: CategoryExpenseDAO):ILocalCategoryExpensesDataSource {
    private val allTeams: Flow<List<CategoryExpenseEntity>> = categoryExpensesDAO.getAll()

    override fun addCategory(categoryExpenseView: ICategoryExpenseView) {
        categoryExpensesDAO.save(CategoryExpenseHelper.getCategoryExpenseEntityFromCategoryExpenseView(categoryExpenseView))
    }

    override fun getCategoriesExpenses(): LiveData<List<ICategoryExpenseView?>> {
        return Transformations.map(allTeams.asLiveData(),CategoryExpenseHelper::getListCategoryExpenseEntityFromListCategoryExpenseView)
    }
}