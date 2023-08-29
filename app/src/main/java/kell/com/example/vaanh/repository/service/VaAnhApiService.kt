package kell.com.example.vaanh.repository.service

import kell.com.example.vaanh.model.AccountProfile
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.model.ResponseUtil
import kell.com.example.vaanh.model.SignUpRequest
import kell.com.example.vaanh.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.*

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