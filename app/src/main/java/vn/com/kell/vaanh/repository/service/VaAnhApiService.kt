package vn.com.kell.vaanh.repository.service

import retrofit2.Response
import retrofit2.http.*
import vn.com.kell.vaanh.model.AccountProfile
import vn.com.kell.vaanh.model.AuthenticationRequest
import vn.com.kell.vaanh.model.AuthenticationResponse
import vn.com.kell.vaanh.model.ResponseUtil
import vn.com.kell.vaanh.model.SignUpRequest
import vn.com.kell.vaanh.model.SignUpResponse

interface VaAnhApiService {
    @POST("auth/login")
    suspend fun login(@Body request: AuthenticationRequest): ResponseUtil<AuthenticationResponse>

//    @GET("auth/refresh")
//    suspend fun refreshToken(@Header("Authorization") token: String, ): Response<AuthenticationResponse>

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("user/profile")
    suspend fun getProfile(): Response<ResponseUtil<AccountProfile>>

    @POST("user/profile/update")
    suspend fun updateProfile(): Response<ResponseUtil<AccountProfile>>
}