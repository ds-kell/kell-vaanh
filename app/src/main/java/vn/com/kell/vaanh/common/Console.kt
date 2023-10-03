package vn.com.kell.vaanh.common

import android.util.Log

object Console {
    fun log(value: String) {
        Log.e("Kell-log", "$value")
    }

    fun log(value: Int) {
        Log.e("Kell-log", "$value")
    }

    fun log(value: Float) {
        Log.e("Kell-log", "$value")
    }

    fun log(value: Double) {
        Log.e("Kell-log", "$value")
    }

    fun log(value: Long) {
        Log.e("Kell-log", "$value")
    }
}