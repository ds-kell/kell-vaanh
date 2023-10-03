package vn.com.kell.vaanh.common

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import kotlinx.parcelize.IgnoredOnParcel

data class ConfirmRequest(
    val icon: Drawable,
    val title: String? = null,
    val message: String? = null,
    val cancelable: Boolean = false,
    val negative: String? = null,
    val positive: String? = null,
    val neutral: String? = null,
    @IgnoredOnParcel @Transient val onPositiveSelected: (() -> Unit)? = null,
    @IgnoredOnParcel @Transient val onNegativeSelected: (() -> Unit)? = null,
    @IgnoredOnParcel @Transient val onNeutralSelected: (() -> Unit)? = null,
)

fun ConfirmRequest.buildAlertDialog(context: Context, @StyleRes style: Int = 0): AlertDialog =
    AlertDialog.Builder(context, style).apply {
        setTitle(title)
        setIcon(icon)
        setMessage(message)
        setCancelable(cancelable)
        positive?.let {
            setPositiveButton(positive) { dialog, _ ->
                dialog.dismiss()
                onPositiveSelected?.invoke()
            }
        }
    }.create()