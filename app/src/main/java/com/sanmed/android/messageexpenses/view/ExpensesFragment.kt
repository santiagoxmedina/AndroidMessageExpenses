package com.sanmed.android.messageexpenses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentExpensesBinding
import com.sanmed.android.messageexpenses.viewmodel.ExpensesViewModel

class ExpensesFragment : Fragment() {
    private lateinit var viewModel: ExpensesViewModel
    private lateinit var binding : FragmentExpensesBinding
    private lateinit var adapter : ExpensesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expenses,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(ExpensesViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExpensesAdapter( DiffExpense())
        binding.listExpensesRecyclerView.adapter = adapter
        initSubscribers();
    }

    private fun initSubscribers() {
        viewModel.expenses.observe(viewLifecycleOwner,this::onExpensesChanged)
    }

    private fun onExpensesChanged( expenses:List<IExpense?>) {
        adapter.submitList(expenses)
    }
}