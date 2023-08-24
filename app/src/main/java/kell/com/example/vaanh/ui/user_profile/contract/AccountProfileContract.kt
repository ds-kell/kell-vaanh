package kell.com.example.vaanh.ui.user_profile.contract

import kell.com.example.vaanh.model.AccountProfile
import kotlinx.coroutines.flow.StateFlow

interface AccountProfileContract {
    suspend fun getAccountProfile(): StateFlow<AccountProfile?>

    fun isUpdate(): StateFlow<Boolean>
    fun statusUpdate()
}