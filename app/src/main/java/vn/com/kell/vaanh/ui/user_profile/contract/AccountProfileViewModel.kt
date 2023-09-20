package vn.com.kell.vaanh.ui.user_profile.contract

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vn.com.kell.vaanh.common.VaViewModel
import vn.com.kell.vaanh.interator.GetAccountProfileUseCase
import vn.com.kell.vaanh.model.AccountProfile
import javax.inject.Inject

@HiltViewModel
class AccountProfileViewModel @Inject constructor(
    private val getAccountProfileUseCase: GetAccountProfileUseCase,
) :
    VaViewModel(), AccountProfileContract {

    private val stateAccountProfile = MutableStateFlow<AccountProfile?>(
        AccountProfile(
            "Full name",
            "Phone number",
            "Date of Birth",
            "Gender",
            "Avatar"
        )
    )

    private val stateUpdate = MutableStateFlow(false)
    override suspend fun getAccountProfile(): StateFlow<AccountProfile?> = stateAccountProfile
    override fun isUpdate(): StateFlow<Boolean> = stateUpdate
    override fun statusUpdate() {
        stateUpdate.value = !stateUpdate.value
    }

    init {
        vaViewModelScope {
            val response = getAccountProfileUseCase.execute()
            if (response != null) {
                if (response.message == "success") {
                    stateAccountProfile.value = response.data
                } else {
                    stateAccountProfile.value = null
                }
            }
        }
    }
}