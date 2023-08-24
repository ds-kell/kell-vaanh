package kell.com.example.vaanh.ui.authentication.login.contract

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kell.com.example.vaanh.interator.LoginUseCase
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.model.AuthenticationResponse
import kell.com.example.vaanh.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase, private val sessionManager: SessionManager
) : ViewModel(), LogInContract {

    private val stateUsername = MutableStateFlow("")
    private val statePassword = MutableStateFlow("")
    private val stateEmail = MutableStateFlow("")
    private val stateMessage = MutableStateFlow("")


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
    override fun getEmail(): MutableStateFlow<String> = stateEmail
    override fun getMessage(): StateFlow<String> = stateMessage

    override fun onSubmit(toHome: (username: String) -> Unit) {
        if (stateUsername.value == "" || statePassword.value == "") {
            stateMessage.value = "Please enter all information"
        } else {
            val authRequest = AuthenticationRequest(
                stateUsername.value, statePassword.value
            )
            viewModelScope.launch {
                val response = loginUseCase.execute(authRequest)
                Log.d("kell-log", "$response")
                if (response != null) {
                    if (response.message == "success") {
                        val authResponse: AuthenticationResponse = response.data
                        sessionManager.saveAuthToken(authResponse.token)
                        toHome(stateUsername.value)
                    } else {
                        stateMessage.value = response.message
                    }
                } else {
                    stateMessage.value = "Username or password is invalid!"
                }
            }
        }
    }
}