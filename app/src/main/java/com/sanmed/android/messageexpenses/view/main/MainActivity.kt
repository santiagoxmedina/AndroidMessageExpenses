package com.sanmed.android.messageexpenses.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ActivityMainBinding
import com.sanmed.android.messageexpenses.viewmodel.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    val viewModel by viewModels<MainActivityViewModel>()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        viewModel.checkingPermission.observe(this, this::onCheckingPermission)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.summary_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_filter -> {
            showFilterPopupMenu(mBinding.toolbar)
            true
        }
        R.id.action_update -> {
            viewModel.onUpdate()
            true
        }
        R.id.action_group_by_month -> {
            viewModel.onGroupByMonth()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showFilterPopupMenu(view: View?){
        view?.let {
            val popup = PopupMenu(this, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.filter_menu, popup.menu)
            popup.setOnMenuItemClickListener (this::onOptionsItemSelected)
            popup.show()
        }
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
                val snackbar = Snackbar.make(mBinding.root,R.string.permission_needed, Snackbar.LENGTH_SHORT)
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