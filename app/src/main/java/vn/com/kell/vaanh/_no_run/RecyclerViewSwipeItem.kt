package vn.com.kell.vaanh._no_run

import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kell.com.example.vaanh.R


fun RecyclerView.setUpSwipe() {
    val swipeHelper = object : SwipeHelper(context, 0, ItemTouchHelper.LEFT) {
        override fun instantiateUnderlayButton(
            viewHolder: RecyclerView.ViewHolder,
            underlayButtons: MutableList<UnderlayButton>
        ) {
            underlayButtons.add(
                UnderlayButton(
                    "Button 1",
                    R.color.kafka_hair,
                    object : UnderlayButtonClickListener {
                        override fun onClick(pos: Int) {
                            // Handle button click
                        }
                    }
                )
            )

            // Add more buttons as needed
        }
    }

    val itemTouchHelper = ItemTouchHelper(swipeHelper)
    itemTouchHelper.attachToRecyclerView(this)
}
