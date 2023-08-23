package kell.com.example.vaanh.ui.authentication.login.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kell.com.example.vaanh.interator.LoginUseCase
import kell.com.example.vaanh.model.AuthenticationRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), LogInContract {

    private val stateUsername = MutableStateFlow("")
    private val statePassword = MutableStateFlow("")
    private val stateEmail = MutableStateFlow("")


    override fun setUsername(username: String) {
        stateUsername.value = username
    }

    override fun setPassword(password: String) {
        statePassword.value = password
    }

    override fun setEmail(email: String) {
        stateEmail.value = email
    }

    override fun getUsername(): MutableStateFlow<String> = stateUsername

    override fun getPassword(): MutableStateFlow<String> = statePassword
    override fun getEmail(): StateFlow<String> = stateEmail

    override fun onSubmit(toHome: (username: String) -> Unit) {
        val authRequest = AuthenticationRequest(
            stateUsername.value,
            statePassword.value
        )
        viewModelScope.launch {
            val response = loginUseCase.execute(authRequest)
            if (response != null) {
                toHome(stateUsername.value)
            } else {

            }
        }
    }
}