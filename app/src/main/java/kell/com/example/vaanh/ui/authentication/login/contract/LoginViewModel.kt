package kell.com.example.vaanh.ui.authentication.login.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kell.com.example.vaanh.interator.LoginUseCase
import kell.com.example.vaanh.model.AuthenticationRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : LoginContract,
    ViewModel() {

    private var stateUsername = MutableStateFlow("")

    private var statePassword = MutableStateFlow("")


    override fun getUsername(): StateFlow<String> = stateUsername

    override fun getPassword(): StateFlow<String> = statePassword
    override fun setUsername(username: String) {
        stateUsername.value = username
    }

    override fun setPassword(password: String) {
        statePassword.value = password
    }

    override fun onSubmit() {
        val username = stateUsername.value
        val password = statePassword.value
        Timber.tag("kell-log").d("$username, $password")
        viewModelScope.launch {
            try {
                loginUseCase.run(AuthenticationRequest(username, password))
                Timber.tag("kell-log").d("$username, $password")
            } catch (e: Exception) {
                Timber.tag("kell-log").d("Oh no: $e")
            }
        }
    }
}