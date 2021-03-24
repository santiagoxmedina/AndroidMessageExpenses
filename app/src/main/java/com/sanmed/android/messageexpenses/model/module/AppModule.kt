package com.sanmed.android.messageexpenses.model.module

import com.sanmed.android.messageexpenses.view.add_expense.AddExpenseViewModel
import com.sanmed.android.messageexpenses.view.add_expense.IAddExpenseView
import com.sanmed.android.messageexpenses.view.add_expense.IAddExpenseViewModel
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
    abstract fun bindIAddExpenseView(view: AddExpenseViewModel): IAddExpenseViewModel

}