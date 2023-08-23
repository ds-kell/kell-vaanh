package kell.com.example.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.SignUpRequest
import kell.com.example.vaanh.model.SignUpResponse
import kell.com.example.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class SignUpUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(signUpRequest: SignUpRequest): SignUpResponse? {
        repository.signUp(signUpRequest).let { response ->
            if (response.isSuccessful) {
                return response.body()
            } else {
                throw Exception("Account creation failed")
            }
        }
    }
}
