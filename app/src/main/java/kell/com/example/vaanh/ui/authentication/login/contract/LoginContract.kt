package kell.com.example.vaanh.ui.authentication.login.contract

import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    fun onSubmit()
    fun getUsername(): StateFlow<String>

    fun getPassword(): StateFlow<String>
}