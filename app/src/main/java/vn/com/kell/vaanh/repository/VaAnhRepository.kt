package vn.com.kell.vaanh.repository

import retrofit2.Response
import vn.com.kell.vaanh.model.AccountProfile
import vn.com.kell.vaanh.model.AuthenticationRequest
import vn.com.kell.vaanh.model.AuthenticationResponse
import vn.com.kell.vaanh.model.Product
import vn.com.kell.vaanh.model.ResponseUtils
import vn.com.kell.vaanh.model.SignUpRequest
import vn.com.kell.vaanh.model.SignUpResponse

interface VaAnhRepository {
    suspend fun login(param: AuthenticationRequest): ResponseUtils<AuthenticationResponse>
    suspend fun signUp(param: SignUpRequest): Response<SignUpResponse>
    suspend fun getProfile(): Response<ResponseUtils<AccountProfile>>
    suspend fun getProducts(): ResponseUtils<List<Product>>
}