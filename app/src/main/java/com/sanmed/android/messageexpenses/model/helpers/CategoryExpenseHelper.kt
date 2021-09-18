package com.sanmed.android.messageexpenses.model.helpers

import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity
import com.sanmed.android.messageexpenses.view.categories_expenses.CategoryExpenseView
import com.sanmed.android.messageexpenses.view.categories_expenses.ICategoryExpenseView
import java.math.BigDecimal

class CategoryExpenseHelper {

    companion object{
        fun getCategoryExpenseEntityFromCategoryExpenseView(categoryExpenseView: ICategoryExpenseView):CategoryExpenseEntity{
            return  CategoryExpenseEntity(categoryExpenseView.getId(),categoryExpenseView.getName(),categoryExpenseView.getAmount())
        }

        fun getCategoryExpenseViewFromCategoryExpenseEntity(categoryExpenseEntity: CategoryExpenseEntity): ICategoryExpenseView {
            return CategoryExpenseView(categoryExpenseEntity.id,categoryExpenseEntity.name,categoryExpenseEntity.amount,0f,0)
        }
        fun getListCategoryExpenseViewFromListCategoryExpenseEntity(categoryExpenseView: List<ICategoryExpenseView?>):List<CategoryExpenseEntity>{
            val result = mutableListOf<CategoryExpenseEntity>()
            categoryExpenseView.forEach {
                it?.apply {
                    result.add(getCategoryExpenseEntityFromCategoryExpenseView(it))
                }
            }
            return result
        }

        fun getListCategoryExpenseEntityFromListCategoryExpenseView(categoriesExpenseEntity: List<CategoryExpenseEntity?>):List<ICategoryExpenseView>{
            val result = mutableListOf<ICategoryExpenseView>()
            categoriesExpenseEntity.forEach {
                it?.apply {
                    result.add(getCategoryExpenseViewFromCategoryExpenseEntity(it))
                }
            }
            return result
        }

        fun create(name: String, amount: BigDecimal): ICategoryExpenseView {
            return CategoryExpenseView(UUIDHelper.generate(),name,amount,0f,0)
        }
    }
}