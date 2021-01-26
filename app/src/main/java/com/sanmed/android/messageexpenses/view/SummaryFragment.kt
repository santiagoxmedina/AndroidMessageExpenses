package com.sanmed.android.messageexpenses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentSummaryBinding
import com.sanmed.android.messageexpenses.viewmodel.SummaryViewModel

class SummaryFragment : Fragment() {


    private lateinit var viewModel: SummaryViewModel
    private lateinit var binding : FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToExpenses.observe(viewLifecycleOwner,this::onNavigateToExpenses)

    }

    private fun onNavigateToExpenses(navigateToExpenses:Boolean) {
        if(navigateToExpenses){
            navigateToExpenses();
            viewModel.onNavigateToExpensesCompleted()
        }
    }

    private fun navigateToExpenses() {
        Navigation.findNavController(requireView()).navigate(SummaryFragmentDirections.actionSummaryFragmentToExpensesFragment())
    }

}