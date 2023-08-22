package kell.com.example.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun run(request: AuthenticationRequest) {
        repository.login(request).let { response ->
            val token = response.accessToken.token
            if (token.isEmpty()) {
                throw Exception("Đăng nhập không thành công")
            } else {
            }
        }
    }
}