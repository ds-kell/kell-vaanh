package vn.com.kell.vaanh.binding

import kotlinx.coroutines.flow.MutableStateFlow
import vn.com.kell.vaanh.common.Console


class VaAnhNumberBinding {
    private val _stateValue = MutableStateFlow(10)

    val stateValue = _stateValue.value

    fun updateStateValue(value: Int) {
        _stateValue.value += value
        Console.log(_stateValue.value)
    }
}