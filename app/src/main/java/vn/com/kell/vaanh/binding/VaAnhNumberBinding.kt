package vn.com.kell.vaanh.binding

import kotlinx.coroutines.flow.MutableStateFlow

class VaAnhNumberBinding {
    private val stateValue: MutableStateFlow<String> = MutableStateFlow("0")
    fun setStateValue(value: String) {
        stateValue.value = "${stateValue.value.toInt() + value.toInt()}"
    }

    fun getStateValue(): String {
        return stateValue.value
    }

    fun validation() {

    }
    fun updateStateValue(value: Int) {
        stateValue.value = "${stateValue.value.toInt() + value}"
    }
}