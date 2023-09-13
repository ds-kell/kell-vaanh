package vn.com.kell.vaanh.ui.product.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.interator.GetProductDetailUseCase
import vn.com.kell.vaanh.interator.GetProductUseCase
import vn.com.kell.vaanh.model.ProductDTO
import vn.com.kell.vaanh.model.ProductDetailDTO
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val getProductUseCase: GetProductUseCase
) :
    ViewModel(), ProductDetailContract {
    private val stateProduct = MutableStateFlow<Int?>(null)
    private val stateProductDetail: StateFlow<List<ProductDetailDTO>> = stateProduct.map {
        it ?: return@map emptyList()
        getProductDetailUseCase.execute(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    override fun getProductDetail(): StateFlow<List<ProductDetailDTO>> = stateProductDetail

    override fun getProductId(productId: Int) {
        stateProduct.value = productId
    }

    private val stateProducts: MutableStateFlow<List<ProductDTO>> = MutableStateFlow(emptyList())
    override fun getProducts(): StateFlow<List<ProductDTO>> = stateProducts

    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch {
            val response = getProductUseCase.execute()
            val tmp = mutableListOf<ProductDTO>()
            tmp.addAll(response)
            tmp.addAll(response)
            tmp.addAll(response)
            stateProducts.value = tmp
        }
    }
}
