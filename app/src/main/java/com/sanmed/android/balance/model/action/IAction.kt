package com.sanmed.android.balance.model.action

interface IAction<X,Y> {

    fun onAction(x:X,y:Y)
}