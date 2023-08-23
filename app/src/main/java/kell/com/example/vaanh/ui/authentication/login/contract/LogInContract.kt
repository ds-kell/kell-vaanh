package kell.com.example.vaanh.ui.authentication.login.contract

import kotlinx.coroutines.flow.StateFlow

interface LogInContract {
    fun onSubmit(toHome: (username: String) -> Unit)
    fun setUsername(username: String)
    fun setPassword(password: String)
    fun setEmail(email: String)
    fun getUsername(): StateFlow<String>
    fun getPassword(): StateFlow<String>
    fun getEmail(): StateFlow<String>
}