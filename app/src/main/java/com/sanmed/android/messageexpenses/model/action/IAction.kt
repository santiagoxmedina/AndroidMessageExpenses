package com.sanmed.android.messageexpenses.model.action

interface IAction<X,Y> {

    fun onAction(x:X,y:Y)
}