package vn.com.kell.vaanh.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import vn.com.kell.vaanh.adapter.loadmore.LastItemListener

@Suppress("MemberVisibilityCanBePrivate")
abstract class StickyItemHandler(layoutManager: RecyclerView.LayoutManager) :
    LastItemListener(layoutManager) {
    private var isShown = false
    protected val stickyThreshold: Int
        get() = 0

    protected fun conditionShow(index: Int): Boolean {
        return index >= stickyThreshold
    }

    protected fun conditionHide(index: Int): Boolean {
        return index < stickyThreshold
    }

    override fun onLastItemIndexChanged(newIndex: Int) {
        if (isShown) {
            if (conditionHide(newIndex)) {
                onHide()
                isShown = false
            }
        } else {
            if (conditionShow(newIndex)) {
                onShow()
                isShown = true
            }
        }
    }

    protected abstract fun onShow()
    protected abstract fun onHide()
}