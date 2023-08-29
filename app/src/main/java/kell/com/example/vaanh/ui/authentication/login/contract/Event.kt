package kell.com.example.vaanh.ui.authentication.login.contract

class Event<T>(private val content: T) {
    private var hasBeenHandled = false

    val contentIfNotHandled: T?
        get() = if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
}
