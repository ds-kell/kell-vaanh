package kell.com.example.vaanh.interator

import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.model.ResponseUtil
import kell.com.example.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(authRequest: AuthenticationRequest): ResponseUtil<AuthenticationResponse>? {
        repository.login(authRequest).let {
            Log.d("kell-log", "$it")
            return it.body()
        }
    }
}
