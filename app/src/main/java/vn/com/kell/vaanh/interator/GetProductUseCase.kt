package vn.com.kell.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import vn.com.kell.vaanh.model.Product
import vn.com.kell.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class GetProductUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(): List<Product> =
        repository.getProducts().let { response ->
            return response.data
        }
}