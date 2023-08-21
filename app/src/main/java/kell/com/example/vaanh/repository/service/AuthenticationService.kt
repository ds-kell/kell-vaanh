package kell.com.example.vaanh.repository.service

import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import retrofit2.http.*

interface AuthenticationService {
    @POST("auth/login")
    suspend fun login(@Body request: AuthenticationRequest): AuthenticationResponse
}