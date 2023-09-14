package vn.com.kell.vaanh.binding

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.FragmentActivity


object VaAnhTextBindingAdapter {
    fun AppCompatEditText.bindImeOptions(imeOptions: Int?) {
        if (imeOptions == null) {
            setOnEditorActionListener(null)
            setImeOptions(EditorInfo.IME_ACTION_UNSPECIFIED)
        } else {
            setImeOptions(imeOptions)
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    dismissKeyboard()
                }
                false
            }
        }
    }

    private fun View.dismissKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
            hideSoftInputFromWindow(windowToken, 0)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun EditText.hideKeyboardAndBackButton() {
        var count = 0
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (hasFocus()) {
                    val imm =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(windowToken, 0)
                    count = 1
                } else if (count == 1) {
                    (context as Activity).onBackPressed()
                }
            }
            true
        }
    }

    fun FragmentActivity.dispatchBackPressed() = onBackPressedDispatcher.onBackPressed()
}
