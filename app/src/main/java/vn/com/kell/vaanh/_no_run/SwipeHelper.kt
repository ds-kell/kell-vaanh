package vn.com.kell.vaanh._no_run

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeHelper(context: Context?, dragDir: Int, swipeDir: Int) :
    ItemTouchHelper.SimpleCallback(dragDir, swipeDir) {

    abstract fun instantiateUnderlayButton(viewHolder: RecyclerView.ViewHolder, underlayButtons: MutableList<UnderlayButton>)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Do something when item is swiped
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            val buttons = mutableListOf<UnderlayButton>()
            instantiateUnderlayButton(viewHolder, buttons)

            drawButtons(c, itemView, buttons, dX)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawButtons(
        c: Canvas,
        itemView: View,
        buttons: List<UnderlayButton>,
        dX: Float
    ) {
        val buttonWidth = itemView.width / buttons.size

        var right = itemView.right.toFloat()
        val dButtonWidth = -1 * dX / buttons.size

        for (button in buttons) {
            val left = right - buttonWidth
            button.draw(c, RectF(left, itemView.top.toFloat(), right, itemView.bottom.toFloat()))
            right = left
        }
    }

    interface UnderlayButtonClickListener {
        fun onClick(pos: Int)
    }

    data class UnderlayButton(
        val text: String,
        val color: Int,
        val clickListener: UnderlayButtonClickListener
    ) {
        fun draw(c: Canvas, rect: RectF) {
            val paint = Paint()

            // Draw button background
            paint.color = color
            c.drawRect(rect, paint)

            // Draw button text
            paint.color = Color.WHITE
            paint.textSize = 40f
            val textRect = Rect()
            paint.getTextBounds(text, 0, text.length, textRect)
            val x = rect.left + (rect.width() - textRect.width()) / 2
            val y = rect.top + (rect.height() + textRect.height()) / 2
            c.drawText(text, x, y, paint)
        }
    }
}
