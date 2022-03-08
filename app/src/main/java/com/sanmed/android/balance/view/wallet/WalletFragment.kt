package com.sanmed.android.balance.view.wallet

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sanmed.android.balance.viewmodel.wallet.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : Fragment() {

    val viewModel by viewModels<WalletViewModel>()
    //private lateinit var mBinding: FragmentWalletBinding

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel
        return mBinding.root
    }*/
}