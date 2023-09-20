package vn.com.kell.vaanh.ui.home.contract

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vn.com.kell.vaanh.common.VaViewModel
import vn.com.kell.vaanh.interator.GetProductUseCase
import vn.com.kell.vaanh.model.ProductDTO
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
) : VaViewModel(), HomeContract {
    private val stateProduct: MutableStateFlow<List<ProductDTO>> = MutableStateFlow(emptyList())
    override fun getProducts(): StateFlow<List<ProductDTO>> = stateProduct

    init {
        initData()
    }

    private fun initData() {
        vaViewModelScope {
            val response = getProductUseCase.execute()
            val tmp = mutableListOf<ProductDTO>()
            tmp.addAll(response)
            tmp.addAll(response)
            tmp.addAll(response)
            stateProduct.value = tmp
        }
    }
}
