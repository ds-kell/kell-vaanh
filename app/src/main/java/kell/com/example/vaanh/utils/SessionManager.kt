package kell.com.example.vaanh.utils

import android.content.Context
import android.content.SharedPreferences
import kell.com.example.vaanh.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val ACCESS_TOKEN = "access_token"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun deleteToken() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}