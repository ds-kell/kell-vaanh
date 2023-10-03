package vn.com.kell.vaanh.ui.authentication.signup.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.interator.user.SignUpUseCase
import vn.com.kell.vaanh.model.SignUpRequest
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel(), SignUpContract {

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

    override fun getEmail(): MutableStateFlow<String> = stateEmail
    override fun onSubmit(toHome: (username: String) -> Unit) {
        val signUpRequest = SignUpRequest(
            stateEmail.value,
            stateUsername.value,
            statePassword.value
        )
        viewModelScope.launch {
            val response = signUpUseCase.execute(signUpRequest)
            if (response != null) {
                if (response.message == "Created") {

                }
            } else {

            }
        }
    }

    fun doSomething() {

    }
}