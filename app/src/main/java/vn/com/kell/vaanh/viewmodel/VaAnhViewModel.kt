package vn.com.kell.vaanh.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class VaAnhViewModel(application: Application) : ViewModel() {
    fun vaScopeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {

            }
        }.invokeOnCompletion { throwable ->
            if (throwable != null) {

            }
        }
    }

}
