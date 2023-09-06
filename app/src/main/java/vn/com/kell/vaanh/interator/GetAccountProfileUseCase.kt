package vn.com.kell.vaanh.interator

import dagger.hilt.android.scopes.ViewModelScoped
import vn.com.kell.vaanh.model.AccountProfile
import vn.com.kell.vaanh.model.ResponseUtil
import vn.com.kell.vaanh.repository.VaAnhRepository
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
