package kell.com.example.vaanh.repository.impl

import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.network.RetrofitClient
import kell.com.example.vaanh.repository.UserRepository
import kell.com.example.vaanh.repository.service.AuthenticationService

class UserRepositoryImpl : UserRepository {
    private val myRetrofit = RetrofitClient.newClient()

    private val service: AuthenticationService =
        myRetrofit.create(AuthenticationService::class.java)

    override suspend fun login(param: AuthenticationRequest): AuthenticationResponse =
        service.login(request = param)

}