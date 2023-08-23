package kell.com.example.vaanh.repository

import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.model.SignUpRequest
import kell.com.example.vaanh.model.SignUpResponse
import kell.com.example.vaanh.model.UserProfile
import retrofit2.Response

interface VaAnhRepository {
    suspend fun login(param: AuthenticationRequest): Response<AuthenticationResponse>
    suspend fun signUp(param: SignUpRequest): Response<SignUpResponse>
    suspend fun getProfile(): Response<UserProfile>
}