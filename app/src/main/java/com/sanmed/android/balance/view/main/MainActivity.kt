package com.sanmed.android.balance.view.main

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.sanmed.android.balance.R
import com.sanmed.android.balance.databinding.ActivityMainBinding
import com.sanmed.android.balance.view.permission.IPermissionView
import com.sanmed.android.balance.viewmodel.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity(override var registerForActivityResult: ActivityResultLauncher<Array<String>>? = null) :
    AppCompatActivity(), IPermissionView {

    lateinit var mBinding: ActivityMainBinding
    override val viewModel by viewModels<MainActivityViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        setButtonNavigation()
        onInitPermission()
    }

    private fun setButtonNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun getPermissionView(): View {
        return mBinding.root
    }

    override fun getPermissionContext(): Context {
        return this
    }

    override fun getPermissionLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun initRegisterForActivityResult(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            onPermissionResult(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.summary_menu, menu)
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

    private fun showFilterPopupMenu(view: View?) {
        view?.let {
            val popup = PopupMenu(this, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.filter_menu, popup.menu)
            popup.setOnMenuItemClickListener(this::onOptionsItemSelected)
            popup.show()
        }
    }

}
