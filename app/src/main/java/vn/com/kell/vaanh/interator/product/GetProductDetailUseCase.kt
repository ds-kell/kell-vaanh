package vn.com.kell.vaanh.interator.product

import dagger.hilt.android.scopes.ViewModelScoped
import vn.com.kell.vaanh.model.ProductDetailDTO
import vn.com.kell.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class GetProductDetailUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(productId: Int): List<ProductDetailDTO> =
        repository.getProductDetail(productId).let { response ->
            return response.data
        }
}