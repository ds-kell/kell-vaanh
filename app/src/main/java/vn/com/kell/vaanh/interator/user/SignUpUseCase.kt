package vn.com.kell.vaanh.interator.user

import dagger.hilt.android.scopes.ViewModelScoped
import vn.com.kell.vaanh.model.SignUpRequest
import vn.com.kell.vaanh.model.SignUpResponse
import vn.com.kell.vaanh.repository.VaAnhRepository
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
