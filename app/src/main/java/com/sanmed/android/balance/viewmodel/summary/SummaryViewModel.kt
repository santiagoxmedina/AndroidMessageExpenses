package com.sanmed.android.balance.viewmodel.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.balance.model.repository.IExpensesRepository
import com.sanmed.android.balance.view.summary.ISummaryItemView
import com.sanmed.android.balance.viewmodel.permission.IPermissionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class SummaryViewModel @Inject constructor(
    private val _expensesRepository: IExpensesRepository
) :
    ViewModel(), ISummaryViewModel, IPermissionViewModel {
    private val _checkingPermission = MutableLiveData<List<String>>()
    private val _navigateToExpenses = MutableLiveData<Boolean>()
    val navigateToExpenses: LiveData<Boolean> get() = _navigateToExpenses
    val items: LiveData<List<ISummaryItemView>> get() = _expensesRepository.getSummaryExpenses()
    var hasPermission: Boolean = false

    override fun onNavigateToExpenses() {
        _navigateToExpenses.value = true
    }

    override fun onNavigateToExpensesCompleted() {
        _navigateToExpenses.value = false
    }

    override fun getSummaryItemList(): LiveData<List<ISummaryItemView>> {
        return items
    }

    override fun onUpdate() {
        checkPermissionGranted(getPermissionName())
    }

    private fun getExpenses() {
        GlobalScope.launch {
            _expensesRepository.updateSummaryExpenses()
        }
    }

    override fun getCheckingPermission() = _checkingPermission

    override fun getPermissionName() = arrayListOf("android.permission.READ_SMS")

    override fun setPermission(permission: Boolean) {
        hasPermission = permission
    }

    override fun hasPermission(): Boolean {
        return hasPermission
    }

    override fun onContinueAfterPermissionIsGranted() {
        getExpenses()
    }
}