package com.sanmed.android.messageexpenses.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.view.*
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.ActivityMainBinding
import com.sanmed.android.messageexpenses.model.entities.CategoryExpenseEntity
import com.sanmed.android.messageexpenses.view.utilities.ExpensesTextUtility
import com.sanmed.android.messageexpenses.viewmodel.main.MainActivityViewModel
import com.sanmed.android.messageexpenses.viewmodel.summary.SummaryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
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

}
