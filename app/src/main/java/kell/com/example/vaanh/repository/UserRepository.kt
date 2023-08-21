package kell.com.example.vaanh.repository

import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse

interface UserRepository {
    suspend fun login(param: AuthenticationRequest): AuthenticationResponse

}