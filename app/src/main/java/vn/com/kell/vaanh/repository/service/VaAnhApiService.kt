package vn.com.kell.vaanh.repository.service

import retrofit2.Response
import retrofit2.http.*
import vn.com.kell.vaanh.model.AccountProfile
import vn.com.kell.vaanh.model.AuthenticationRequest
import vn.com.kell.vaanh.model.AuthenticationResponse
import vn.com.kell.vaanh.model.ProductDTO
import vn.com.kell.vaanh.model.ProductDetailDTO
import vn.com.kell.vaanh.model.ResponseUtils
import vn.com.kell.vaanh.model.SignUpRequest
import vn.com.kell.vaanh.model.SignUpResponse

interface VaAnhApiService {
    @POST("auth/login")
    suspend fun login(@Body request: AuthenticationRequest): ResponseUtils<AuthenticationResponse>

//    @GET("auth/refresh")
//    suspend fun refreshToken(@Header("Authorization") token: String, ): Response<AuthenticationResponse>

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("user/profile")
    suspend fun getProfile(): Response<ResponseUtils<AccountProfile>>

    @POST("user/profile/update")
    suspend fun updateProfile(): Response<ResponseUtils<AccountProfile>>

    @POST("products")
    suspend fun getProducts(): ResponseUtils<List<ProductDTO>>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): ResponseUtils<List<ProductDetailDTO>>

    @GET("products/brand/{id}")
    suspend fun getProductOfBrand(@Path("id") id: Int): ResponseUtils<List<ProductDTO>>
}