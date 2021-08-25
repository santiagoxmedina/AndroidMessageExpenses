package com.sanmed.android.messageexpenses.viewmodel.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.android.messageexpenses.model.repository.ICategoryExpensesRepository
import com.sanmed.android.messageexpenses.model.repository.IExpensesRepository
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.view.summary.ISummaryViewModel
import com.sanmed.android.messageexpenses.view.summary.SummaryItemView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(private val _expensesRepository: IExpensesRepository) :
    ViewModel(), ISummaryViewModel {

    private val _navigateToExpenses = MutableLiveData<Boolean>()
    val navigateToExpenses: LiveData<Boolean> get() = _navigateToExpenses
    val items: LiveData<List<ISummaryItemView>> get() = _expensesRepository.getSummaryExpenses()
    var smsPermission = false
    val smsPermissionName = "android.permission.READ_SMS"
    private val _checkingPermission = MutableLiveData<String>()
    val checkingPermission: LiveData<String> get() = _checkingPermission
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
        if (smsPermission) {
            GlobalScope.launch {
                _expensesRepository.updateSummaryExpenses()
            }
        } else {
            checkPermission(smsPermissionName)
        }
    }

    private fun checkPermission(permission: String) {
        _checkingPermission.value = permission
    }

    fun onPermissionGranted(permission: String) {
        if (smsPermissionName == permission) {
            smsPermission = true
            onUpdate()
        }
    }
}