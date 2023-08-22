package kell.com.example.vaanh.repository.impl

import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.repository.VaAnhRepository
import kell.com.example.vaanh.repository.service.VaAnhApiService

class VaAnhRepositoryImpl constructor(private val service: VaAnhApiService) : VaAnhRepository {

    override suspend fun login(param: AuthenticationRequest): AuthenticationResponse =
        service.login(request = param)

}