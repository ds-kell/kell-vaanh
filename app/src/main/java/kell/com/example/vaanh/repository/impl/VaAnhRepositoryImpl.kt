package kell.com.example.vaanh.repository.impl

import kell.com.example.vaanh.model.AccountProfile
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.model.ResponseUtil
import kell.com.example.vaanh.model.SignUpRequest
import kell.com.example.vaanh.model.SignUpResponse
import kell.com.example.vaanh.repository.VaAnhRepository
import kell.com.example.vaanh.repository.service.VaAnhApiService
import retrofit2.Response
import javax.inject.Inject

class VaAnhRepositoryImpl @Inject constructor(private val service: VaAnhApiService) :
    VaAnhRepository {
    override suspend fun login(param: AuthenticationRequest): Response<ResponseUtil<AuthenticationResponse>> =
        service.login(request = param)

    override suspend fun signUp(param: SignUpRequest): Response<SignUpResponse> =
        service.signUp(request = param)

    override suspend fun getProfile(): Response<ResponseUtil<AccountProfile>> = service.getProfile()
}