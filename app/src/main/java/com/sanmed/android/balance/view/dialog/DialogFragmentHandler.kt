package com.sanmed.android.balance.view.dialog

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

open class DialogFragmentHandler<T>(private val fragment: Fragment, private val viewModel:T) :IDialogHandler<T>,LifecycleObserver{
    private var dialog:Dialog? = null

    init {
        fragment.lifecycle.addObserver(this)
    }

    override fun getContext(): Context {
        return fragment.requireContext()
    }

    override fun getDialog(): Dialog? {
        return dialog
    }

    override fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun disMiss(){
        if(dialog!=null && dialog!!.isShowing){
            dialog!!.dismiss()
        }
    }

    override fun getViewModel(): T {
        return viewModel
    }

}