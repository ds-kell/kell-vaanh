package vn.com.kell.vaanh.ui.product.contract

import kotlinx.coroutines.flow.StateFlow
import vn.com.kell.vaanh.model.ProductDetailDTO

interface ProductDetailContract {
    fun getProductDetail(): StateFlow<List<ProductDetailDTO>>
    fun getProductId(productId: Int)

}