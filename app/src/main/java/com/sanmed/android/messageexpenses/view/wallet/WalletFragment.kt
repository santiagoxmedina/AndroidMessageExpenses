package com.sanmed.android.messageexpenses.view.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sanmed.android.messageexpenses.R
import com.sanmed.android.messageexpenses.databinding.FragmentWalletBinding
import com.sanmed.android.messageexpenses.viewmodel.wallet.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : Fragment() {

    val viewModel by viewModels<WalletViewModel>()
    private lateinit var mBinding: FragmentWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel
        return mBinding.root
    }
}