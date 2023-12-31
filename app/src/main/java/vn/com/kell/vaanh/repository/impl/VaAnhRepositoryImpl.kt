package vn.com.kell.vaanh.repository.impl

import retrofit2.Response
import vn.com.kell.vaanh.model.AccountProfile
import vn.com.kell.vaanh.model.AuthenticationRequest
import vn.com.kell.vaanh.model.AuthenticationResponse
import vn.com.kell.vaanh.model.ProductDTO
import vn.com.kell.vaanh.model.ProductDetailDTO
import vn.com.kell.vaanh.model.ResponseUtils
import vn.com.kell.vaanh.model.SignUpRequest
import vn.com.kell.vaanh.model.SignUpResponse
import vn.com.kell.vaanh.repository.VaAnhRepository
import vn.com.kell.vaanh.repository.service.VaAnhApiService
import javax.inject.Inject

class VaAnhRepositoryImpl @Inject constructor(private val service: VaAnhApiService) :
    VaAnhRepository {
    override suspend fun login(param: AuthenticationRequest): ResponseUtils<AuthenticationResponse> =
        service.login(request = param)

    override suspend fun signUp(param: SignUpRequest): Response<SignUpResponse> =
        service.signUp(request = param)

    override suspend fun getProfile(): Response<ResponseUtils<AccountProfile>> =
        service.getProfile()

    override suspend fun getProducts(): ResponseUtils<List<ProductDTO>> = service.getProducts()

    override suspend fun getProductDetail(productId: Int): ResponseUtils<List<ProductDetailDTO>> =
        service.getProductDetail(productId)

    override suspend fun getProductOfBrand(brandId: Int): ResponseUtils<List<ProductDTO>> =
        service.getProductOfBrand(brandId)

    override suspend fun refreshAuthenticationToken(refreshToken: String): ResponseUtils<AuthenticationResponse> =
        service.refreshAuthenticationToken(refreshToken)

}