package vn.com.kell.vaanh.base.viewmodel

import androidx.lifecycle.ViewModel

open class VaBaseViewModel : ViewModel() {

    open fun fetchData() {

    }

    protected fun notifyError() {

    }

    protected fun confirmThenRun() {

    }

    protected fun notifyMessage() {

    }

    protected fun notifySuccess() {

    }
}