package com.sanmed.android.messageexpenses.view.summary

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentSummaryBinding
import com.sanmed.android.messageexpenses.viewmodel.categories_expenses.CategoriesExpensesViewModel
import com.sanmed.android.messageexpenses.viewmodel.summary.SummaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

@AndroidEntryPoint
class SummaryFragment : Fragment() {

    val viewModel by viewModels<SummaryViewModel>()
    private lateinit var binding: FragmentSummaryBinding
    val requestPermissionLauncher =
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToExpenses.observe(viewLifecycleOwner, this::onNavigateToExpenses)
        binding.itemAnimator = SlideInUpAnimator()
        viewModel.checkingPermission.observe(viewLifecycleOwner, this::onCheckingPermission)
    }

    private fun onCheckingPermission(permission: String) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                viewModel.onPermissionGranted(permission)
            }
            shouldShowRequestPermissionRationale(permission) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                val snackbar = Snackbar.make(requireView(),R.string.permission_needed,Snackbar.LENGTH_SHORT)
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

    private fun onNavigateToExpenses(navigateToExpenses: Boolean) {
        if (navigateToExpenses) {
            navigateToExpenses();
            viewModel.onNavigateToExpensesCompleted()
        }
    }

    private fun navigateToExpenses() {
        Navigation.findNavController(requireView())
            .navigate(SummaryFragmentDirections.actionSummaryFragmentToExpensesFragment())
    }

}