package vn.com.kell.vaanh.ui.home.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.interator.GetProductUseCase
import vn.com.kell.vaanh.model.Product
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
) : ViewModel(), HomeContract {
    private val stateProduct: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    override fun getProducts(): StateFlow<List<Product>> = stateProduct

    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch {
            val response = getProductUseCase.execute()
            val tmp = mutableListOf<Product>()
            tmp.addAll(response)
            tmp.addAll(response)
            tmp.addAll(response)
            stateProduct.value = tmp
        }
    }
}
