package kell.com.example.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import kell.com.example.vaanh.model.AccountProfile
import kell.com.example.vaanh.model.ResponseUtil
import kell.com.example.vaanh.repository.VaAnhRepository
import javax.inject.Inject

@ViewModelScoped
class GetAccountProfileUseCase @Inject constructor(private val repository: VaAnhRepository) {
    suspend fun execute(): ResponseUtil<AccountProfile>? {
        repository.getProfile().let { response ->
            if (response.isSuccessful) {
                return response.body()
            } else {
                return null
            }
        }
    }
}
