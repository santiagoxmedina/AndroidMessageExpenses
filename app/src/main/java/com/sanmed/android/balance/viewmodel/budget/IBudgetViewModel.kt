package com.sanmed.android.balance.viewmodel.budget

import androidx.lifecycle.LiveData
import com.sanmed.android.balance.view.expense.ExpenseView

interface IBudgetViewModel {

    /**
     * Show the available amount
     */
    fun getAvailableAmount(): LiveData<String>

    /**
     * Show the list of all expenses
     */
    fun getExpenses(): LiveData<List<ExpenseView>>

    /**
     * When the user want to add new expenses
     */
    fun addExpense()


    /**
     * When the user want to edit an expenses
     */
    fun editExpense(id:String)

    /**
     * Called when the view started
     */
    fun init()

    /**
     * Get the total amount
     */
    fun getTotalAmount(): LiveData<String>

}
