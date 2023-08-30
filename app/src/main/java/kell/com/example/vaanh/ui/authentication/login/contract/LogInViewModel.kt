package kell.com.example.vaanh.ui.authentication.login.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kell.com.example.vaanh.interator.LoginUseCase
import kell.com.example.vaanh.model.AuthenticationRequest
import kell.com.example.vaanh.utils.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase, private val tokenManager: TokenManager
) : ViewModel(), LogInContract {

    private val stateUsername = MutableStateFlow("")
    private val statePassword = MutableStateFlow("")
    private val stateEmail = MutableStateFlow("")
    private val stateMessage = MutableStateFlow("")
    private val stateToActivity = MutableStateFlow(false)

    override fun setUsername(username: String) {
        stateUsername.value = username
    }

    override fun setPassword(password: String) {
        statePassword.value = password
    }

    override fun setEmail(email: String) {
        stateEmail.value = email
    }

    override fun setStateToActivity(status: Boolean) {
        stateToActivity.value = status
    }

    override fun getUsername(): MutableStateFlow<String> = stateUsername

    override fun getPassword(): MutableStateFlow<String> = statePassword
    override fun getEmail(): MutableStateFlow<String> = stateEmail
    override fun getStateToActivity(): StateFlow<Boolean> = stateToActivity

    override fun getMessage(): StateFlow<String> = stateMessage

    override fun onSubmit() {
        if (stateUsername.value == "" || statePassword.value == "") {
            stateMessage.value = "Please enter all information"
        } else {
            viewModelScope.launch {
                loginUseCase.execute(
                    AuthenticationRequest(
                        stateUsername.value,
                        statePassword.value
                    )
                ).also {
                    tokenManager.saveAuthToken(it.accessToken)
                    stateToActivity.value = true
                }
            }.invokeOnCompletion {
                if (it == null) {

                } else {
                    stateMessage.value = "Username or password is invalid!"
                }
            }
        }
    }
}