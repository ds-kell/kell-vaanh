package kell.com.example.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(authRequest: AuthenticationRequest): AuthenticationResponse? {
        repository.login(authRequest).let { response ->
            if (response.isSuccessful) {
                return response.body()
            } else {
                return null
            }
        }
    }
}
