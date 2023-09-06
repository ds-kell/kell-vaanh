package vn.com.kell.vaanh.ui.user_profile.contract

import kotlinx.coroutines.flow.StateFlow
import vn.com.kell.vaanh.model.AccountProfile

interface AccountProfileContract {
    suspend fun getAccountProfile(): StateFlow<AccountProfile?>

    fun isUpdate(): StateFlow<Boolean>
    fun statusUpdate()
}