package com.sanmed.android.balance.model.module

import com.sanmed.android.balance.model.data_source.ExpensesDataSource
import com.sanmed.android.balance.model.data_source.IExpensesDataSource
import com.sanmed.android.balance.model.data_source.ILocalCategoryExpensesDataSource
import com.sanmed.android.balance.model.data_source.LocalCategoryExpensesDataSource
import com.sanmed.android.balance.model.repository.CategoryExpensesRepository
import com.sanmed.android.balance.model.repository.ExpensesRepository
import com.sanmed.android.balance.model.repository.ICategoryExpensesRepository
import com.sanmed.android.balance.model.repository.IExpensesRepository
import com.sanmed.android.balance.viewmodel.add_edit_expense.IAddEditExpenseViewModel
import com.sanmed.android.balance.view.categories_expenses.ICategoryExpenseView
import com.sanmed.android.balance.view.expense.ExpenseView
import com.sanmed.android.balance.viewmodel.add_edit_expense.AddEditCategoryExpenseViewModel
import com.sanmed.android.balance.viewmodel.add_edit_expense.AddEditExpenseViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindAddEditCategoryExpenseView(viewCategory: AddEditCategoryExpenseViewModel): IAddEditExpenseViewModel<ICategoryExpenseView>

    @Singleton
    @Binds
    abstract fun bindAddEditExpenseView(viewCategory: AddEditExpenseViewModel): IAddEditExpenseViewModel<ExpenseView>

    @Singleton
    @Binds
    abstract fun bindICategoryExpensesRepository(repository: CategoryExpensesRepository): ICategoryExpensesRepository

    @Singleton
    @Binds
    abstract fun bindILocalCategoryExpensesDataSource(dataSource: LocalCategoryExpensesDataSource): ILocalCategoryExpensesDataSource

    @Singleton
    @Binds
    abstract fun bindIExpensesRepository(repository: ExpensesRepository): IExpensesRepository

    @Singleton
    @Binds
    abstract fun bindIExpensesDataSource(datasource: ExpensesDataSource): IExpensesDataSource

}