package kell.com.example.vaanh

import android.util.Log

class UserViewModel {
    var log:String = "Hello"
    fun show(message:String) {
        Log.d("kell-log", message)
    }

    fun display(){}
}