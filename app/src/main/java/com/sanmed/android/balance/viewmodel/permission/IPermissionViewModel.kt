package com.sanmed.android.balance.viewmodel.permission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IPermissionViewModel {


    val checkingPermission: LiveData<List<String>> get() = getCheckingPermission()

    fun checkPermission(permission: List<String>) {
        getCheckingPermission().value = permission
    }

    fun getCheckingPermission():MutableLiveData<List<String>>

    fun onPermissionGranted(permission: String) {
        if (getPermissionName().contains(permission)) {
            setPermission(true)
            onContinueAfterPermissionIsGranted()
        }
    }

    fun getPermissionName():List<String>
    fun setPermission(permission:Boolean)
    fun hasPermission():Boolean

    fun checkPermissionGranted(permissionName:List<String>) {
        if (hasPermission()) {
            onContinueAfterPermissionIsGranted()
        } else {
            checkPermission(permissionName)
        }
    }

    fun onContinueAfterPermissionIsGranted()
}

