package com.sanmed.android.balance.viewmodel.wallet

import androidx.lifecycle.ViewModel
import com.sanmed.android.balance.model.repository.IExpensesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val _expensesRepository: IExpensesRepository
) : ViewModel(),IWalletViewModel{
}