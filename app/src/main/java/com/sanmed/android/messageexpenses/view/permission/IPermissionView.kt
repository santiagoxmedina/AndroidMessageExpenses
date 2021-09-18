package com.sanmed.android.messageexpenses.view.permission

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.viewmodel.permission.IPermissionViewModel

interface IPermissionView {

    val viewModel: IPermissionViewModel
    var registerForActivityResult: ActivityResultLauncher<Array<String>>?

    fun onInitPermission() {
        registerForActivityResult = initRegisterForActivityResult()
        viewModel.checkingPermission.observe(
            getPermissionLifecycleOwner(),
            this::onCheckingPermission
        )
    }

    private fun onCheckingPermission(permissions: List<String>) {
        val pendingPermissions = mutableListOf<String>()
        permissions.forEach { permission ->
            when {
                ContextCompat.checkSelfPermission(
                    getPermissionContext(), permission
                ) == PackageManager.PERMISSION_GRANTED -> {
                    viewModel.onPermissionGranted(permission)
                }
                shouldShowRequestPermissionRationale(permission) -> {
                    val snackbar = Snackbar.make(
                        getPermissionView(),
                        R.string.permission_needed,
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.setAction(
                        R.string.accept,
                        onAcceptRequestPermission(arrayListOf(permission))
                    )
                    snackbar.show()
                }
                else -> {
                    pendingPermissions.add(permission)
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.

                }
            }
        }

        if (pendingPermissions.isNotEmpty()) {
            launchPermission(pendingPermissions)
        }
    }

    private fun onAcceptRequestPermission(permission: List<String>): View.OnClickListener {
        return View.OnClickListener {
            launchPermission(permission)
        }
    }

    private fun launchPermission(permission: List<String>) {
        registerForActivityResult?.launch(permission.toTypedArray())
    }

    fun shouldShowRequestPermissionRationale(permission: String): Boolean
    fun getPermissionView(): View
    fun getPermissionContext(): Context
    fun getPermissionLifecycleOwner(): LifecycleOwner
    fun initRegisterForActivityResult(): ActivityResultLauncher<Array<String>>


    fun onPermissionResult(permissions: Map<String, Boolean>) {
        // Handle Permission granted/rejected
        permissions.entries.forEach {
            val permissionName = it.key
            val isGranted = it.value
            if (isGranted) {
                // Permission is granted
                viewModel.onPermissionGranted(permissionName)
            } else {
                // Permission is denied
                val snackbar = Snackbar.make(
                    getPermissionView(),
                    R.string.permission_needed,
                    Snackbar.LENGTH_SHORT
                )
                snackbar.show()
            }
        }
    }
}