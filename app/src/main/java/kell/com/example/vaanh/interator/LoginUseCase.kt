package kell.com.example.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.repository.VaAnhRepository
import kell.com.example.vaanh.utils.TokenManager
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(
    private val repository: VaAnhRepository,
    private val tokenManager: TokenManager
) {
    suspend fun execute(authRequest: AuthenticationRequest) {
        repository.login(authRequest).let { response ->
            val authResponse = response.data
            if (authResponse.accessToken.isEmpty()) {
                throw IllegalStateException("Login request was not successful")
            }
            tokenManager.saveAuthToken(authResponse.accessToken)
        }
    }
}
