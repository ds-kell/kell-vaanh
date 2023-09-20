package vn.com.kell.vaanh.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class VaViewModel : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    protected fun vaViewModelScope(
        runnable: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(exceptionHandler) {
        launch {
            runnable()
        }.invokeOnCompletion {
            if (it == null) {

            } else {
                Console.log("$exceptionHandler")
            }
        }
    }

//    private val job = SupervisorJob()
//    private val context = Dispatchers.IO + job + exceptionHandler
//    protected val coroutineScope = CoroutineScope(context)
//    protected inline fun vaViewModelScope(
//        exceptionHandler: CoroutineExceptionHandler,
//        crossinline execute: suspend CoroutineScope.() -> Unit,
//    ): Job = coroutineScope.launch(exceptionHandler) {
//        this.execute()
//    }
//    val vaViewModelScope = viewModelScope.launch(exceptionHandler) {
//        launch {
//
//        }.invokeOnCompletion {
//
//        }
//    }
//
//    var error = MutableLiveData<String>()
//        protected set
//
//    var isShowLoading = MutableLiveData<Boolean>()
//        protected set
//
//    protected val parentExceptionHandler = CoroutineExceptionHandler { _, t ->
//        val message = t.message ?: "Something went wrong"
//        Console.log("Parent Exception Handler $message")
//        error.postValue(message)
//    }
//
//    protected var parentJob: Job? = null
//
//
//    open fun fetchData() {
//
//
//    }
//
//    protected fun showLoading(isShow: Boolean) {
//        isShowLoading.postValue(isShow)
//    }
//
//    protected fun registerEventParentJobFinish() {
//        parentJob?.invokeOnCompletion {
//            Console.log("Parent jOb finish")
//            showLoading(false)
//        }
//    }
//  private val job = SupervisorJob()
//  private val context = Dispatchers.IO + job + exceptionHandler
//  protected val coroutineScope = CoroutineScope(context)
//

}

