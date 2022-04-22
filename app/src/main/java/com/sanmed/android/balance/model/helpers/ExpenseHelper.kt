package com.sanmed.android.balance.model.helpers

import com.sanmed.android.balance.model.entities.ExpenseEntity
import com.sanmed.android.balance.view.categories_expenses.CategoryExpenseView
import com.sanmed.android.balance.view.expense.ExpenseView
import com.sanmed.android.balance.view.utilities.CurrencyUtilities
import java.math.BigDecimal
import java.util.*

class ExpenseHelper {
    companion object {
        fun getListExpenseEntityFromListExpenseView(expenseEntity: List<ExpenseEntity?>): List<ExpenseView> {
            val result = mutableListOf<ExpenseView>()
            expenseEntity.forEach {
                it?.let {
                    result.add(it.toView())
                }
            }
            return result
        }

        fun create(name: String, amount: BigDecimal): ExpenseView {
            return create(name,amount, Calendar.getInstance())
        }

        fun create(name: String, amount: BigDecimal,date:Calendar): ExpenseView {
            return ExpenseView(UUIDHelper.generate(),name,amount, date)
        }
    }
}

fun ExpenseView.toEntity(): ExpenseEntity {
    return ExpenseEntity(this.id, this.name, this.amount, this.date)
}

fun ExpenseEntity.toView(): ExpenseView {
    return ExpenseView(this.id, this.name, this.amount, this.date)
}
fun BigDecimal.toCurrency(): String {
    return CurrencyUtilities.format(this)
}