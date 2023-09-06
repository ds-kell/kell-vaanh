package vn.com.kell.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import vn.com.kell.vaanh.model.AuthenticationRequest
import vn.com.kell.vaanh.model.AuthenticationResponse
import vn.com.kell.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(
    private val repository: VaAnhRepository
) {
    suspend fun execute(authRequest: AuthenticationRequest): AuthenticationResponse =
        repository.login(authRequest).let { response ->
            val authResponse = response.data
            if (authResponse.accessToken.isEmpty()) {
                throw IllegalStateException("Login request was not successful")
            }
            return authResponse
        }
}
