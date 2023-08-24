package kell.com.example.vaanh.ui.user_profile.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kell.com.example.vaanh.interator.GetAccountProfileUseCase
import kell.com.example.vaanh.model.AccountProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountProfileViewModel @Inject constructor(
    private val getAccountProfileUseCase: GetAccountProfileUseCase,
) :
    ViewModel(), AccountProfileContract {

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
        viewModelScope.launch {
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