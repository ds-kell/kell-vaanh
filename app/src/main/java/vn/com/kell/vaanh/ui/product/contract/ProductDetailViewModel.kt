package vn.com.kell.vaanh.ui.product.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import vn.com.kell.vaanh.interator.GetProductDetailUseCase
import vn.com.kell.vaanh.model.ProductDetailDTO
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val getProductDetailUseCase: GetProductDetailUseCase) :
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

}
