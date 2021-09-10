package com.sanmed.android.messageexpenses.view.permission

import android.content.pm.PackageManager
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.view.summary.ISummaryItemView
import com.sanmed.android.messageexpenses.viewmodel.permission.IPermissionViewModel

class PermissionView(val viewModel: IPermissionViewModel) {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    fun onInit(){
        viewModel.checkingPermission.observe(this, this::onCheckingPermission)
    }

    private fun onCheckingPermission(permission: String) {
        when {
            ContextCompat.checkSelfPermission(
                this, permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                viewModel.onPermissionGranted(permission)
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                val snackbar = Snackbar.make(mBinding.root, R.string.permission_needed, Snackbar.LENGTH_SHORT)
                snackbar.setAction(R.string.accept,onAcceptRequestPermission(permission))
                snackbar.show()
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                launchPermission(permission)
            }
        }
    }

    private fun onAcceptRequestPermission(permission: String): View.OnClickListener? {
        return View.OnClickListener{
            launchPermission(permission)
        }
    }
    private fun launchPermission(permission: String){
        requestPermissionLauncher.launch(permission)
    }
}