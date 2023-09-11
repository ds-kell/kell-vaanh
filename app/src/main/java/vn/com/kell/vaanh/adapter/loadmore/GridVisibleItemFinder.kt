package vn.com.kell.vaanh.adapter.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import vn.com.kell.vaanh.adapter.loadmore.HeadItemListener.IFirstVisibleItemFinder
import vn.com.kell.vaanh.adapter.loadmore.LastItemListener.ILastVisibleItemFinder

internal class GridVisibleItemFinder(private val gridLayoutManager: GridLayoutManager) :
    ILastVisibleItemFinder, IFirstVisibleItemFinder {
    override fun findLastVisibleItemPosition(): Int {
        return gridLayoutManager.findLastVisibleItemPosition()
    }

    override fun findFirstVisibleItemPosition(): Int {
        return gridLayoutManager.findFirstVisibleItemPosition()
    }

}