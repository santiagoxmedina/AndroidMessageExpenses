package com.sanmed.android.messageexpenses.viewmodel.permission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PermissionViewModel {
    var smsPermission: Boolean
     val smsPermissionName: String
        get() = "android.permission.READ_SMS"
    private val _checkingPermission: MutableLiveData<String>
        get() = MutableLiveData<String>()
    val checkingPermission: LiveData<String> get() = _checkingPermission

    fun checkPermission(permission: String) {
        _checkingPermission.value = permission
    }

    fun onPermissionGranted(permission: String) {
        if (smsPermissionName == permission) {
            smsPermission = true
            onContinueAfterPermissionIsGranted()
        }
    }

    fun onContinueAfterPermissionIsGranted()
}