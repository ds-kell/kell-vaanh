package kell.com.example.vaanh.ui.authentication.login.contract

import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    fun onSubmit()
    fun setUsername(username: String)
    fun setPassword(password: String)
    fun getUsername(): StateFlow<String>

    fun getPassword(): StateFlow<String>
}