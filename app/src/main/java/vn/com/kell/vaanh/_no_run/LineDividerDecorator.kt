package vn.com.kell.vaanh._no_run

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kell.com.example.vaanh.R

class LineDividerDecorator(
    context: Context,
    @IntRange(from = 0) @Px private val margin: Int = 0,
    @Px private val thickness: Int = 2,
    @ColorInt color: Int = Color.GRAY,
    private val drawHeader: Boolean = false,
    private val drawFooter: Boolean = false,
    dash: Boolean = false
) : ItemDecoration() {

    private val drawable: Drawable = if (dash) {
        ContextCompat.getDrawable(context.applicationContext, R.drawable.border_cell_day)?.also {
            DrawableCompat.setTint(it, color)
        } ?: ColorDrawable(color)
    } else ColorDrawable(color)

    override fun onDrawOver(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val childCount = parent.childCount
        if (childCount <= 0) return
        val left = parent.paddingLeft + margin
        val right = parent.width - parent.paddingRight - margin
        if (drawHeader) {
            val top = 0
            val bottom = top + thickness
            drawable.setBounds(left, top, right, bottom)
            drawable.draw(c)
        }
        val end = if (drawFooter) childCount else childCount - 1
        (0 until end).forEach {
            val child = parent.getChildAt(it)
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + thickness
            drawable.setBounds(left, top, right, bottom)
            drawable.draw(c)
        }
    }
}