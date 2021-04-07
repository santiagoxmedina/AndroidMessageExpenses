package com.sanmed.android.messageexpenses.model.module

import com.sanmed.android.messageexpenses.model.data_source.ILocalCategoryExpensesDataSource
import com.sanmed.android.messageexpenses.model.data_source.LocalCategoryExpensesDataSource
import com.sanmed.android.messageexpenses.model.repository.CategoryExpensesRepository
import com.sanmed.android.messageexpenses.model.repository.ICategoryExpensesRepository
import com.sanmed.android.messageexpenses.viewmodel.add_category_expense.AddCategoryExpenseViewModel
import com.sanmed.android.messageexpenses.view.add_category_expense.IAddCategoryExpenseViewModel
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
    abstract fun bindIAddExpenseView(viewCategory: AddCategoryExpenseViewModel): IAddCategoryExpenseViewModel

    @Singleton
    @Binds
    abstract fun bindICategoryExpensesRepository(repository:CategoryExpensesRepository): ICategoryExpensesRepository

    @Singleton
    @Binds
    abstract fun bindILocalCategoryExpensesDataSource(dataSource: LocalCategoryExpensesDataSource): ILocalCategoryExpensesDataSource

}