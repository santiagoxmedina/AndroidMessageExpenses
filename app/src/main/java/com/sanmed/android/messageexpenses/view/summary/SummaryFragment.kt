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