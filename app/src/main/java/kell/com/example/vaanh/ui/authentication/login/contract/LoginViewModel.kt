package kell.com.example.vaanh.ui.authentication.login.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kell.com.example.vaanh.VaAnhApplication
import kell.com.example.vaanh.interator.LoginUseCase
import kell.com.example.vaanh.model.AuthenticationRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: VaAnhApplication) : LoginContract,
    ViewModel() {

    private var stateUsername = MutableStateFlow("")

    private var statePassword = MutableStateFlow("")

    @Inject
    lateinit var loginUseCase: LoginUseCase
    override fun getUsername(): StateFlow<String> = stateUsername

    override fun getPassword(): StateFlow<String> = statePassword
    override fun onSubmit() {
        val username = stateUsername.value
        val password = statePassword.value
        viewModelScope.launch {
            loginUseCase.run(AuthenticationRequest(username, password)).also {

            }
        }
    }

}