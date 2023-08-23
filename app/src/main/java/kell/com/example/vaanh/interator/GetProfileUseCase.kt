package kell.com.example.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.UserProfile
import kell.com.example.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class GetProfileUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(): UserProfile? {
        repository.getProfile().let { response ->
            if (response.isSuccessful) {
                return response.body()
            } else {
                return null
            }
        }
    }
}
