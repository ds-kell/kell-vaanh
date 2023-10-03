package vn.com.kell.vaanh.interator.product

import dagger.hilt.android.scopes.ViewModelScoped
import vn.com.kell.vaanh.model.ProductDTO
import vn.com.kell.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class GetProductUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(): List<ProductDTO> =
        repository.getProducts().let { response ->
            return response.data
        }
}