package com.sanmed.android.messageexpenses.view.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentSummaryBinding
import com.sanmed.android.messageexpenses.view.permission.IPermissionView
import com.sanmed.android.messageexpenses.viewmodel.summary.SummaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

@AndroidEntryPoint
class SummaryFragment(override var registerForActivityResult: ActivityResultLauncher<Array<String>>? = null) :
    Fragment(), IPermissionView {

    override val viewModel by viewModels<SummaryViewModel>()
    private lateinit var mBinding: FragmentSummaryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel
        onInitPermission()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToExpenses.observe(viewLifecycleOwner, this::onNavigateToExpenses)
        mBinding.itemAnimator = SlideInUpAnimator()
    }

    private fun onNavigateToExpenses(navigateToExpenses: Boolean) {
        if (navigateToExpenses) {
            navigateToExpenses();
            viewModel.onNavigateToExpensesCompleted()
        }
    }

    private fun navigateToExpenses() {
        /*Navigation.findNavController(requireView())
            .navigate(NavigationSummaryDirections.actionSummaryFragmentToExpensesFragment())*/
    }

    override fun getPermissionView() = mBinding.root

    override fun getPermissionContext() = requireContext()

    override fun getPermissionLifecycleOwner() = viewLifecycleOwner

    override fun initRegisterForActivityResult(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            onPermissionResult(it)
        }
    }


}