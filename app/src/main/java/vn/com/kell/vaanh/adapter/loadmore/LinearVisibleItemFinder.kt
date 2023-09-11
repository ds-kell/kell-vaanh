package vn.com.kell.vaanh.adapter.loadmore

import androidx.recyclerview.widget.LinearLayoutManager
import vn.com.kell.vaanh.adapter.loadmore.HeadItemListener.IFirstVisibleItemFinder
import vn.com.kell.vaanh.adapter.loadmore.LastItemListener.ILastVisibleItemFinder

internal class LinearVisibleItemFinder(private val linearLayoutManager: LinearLayoutManager) :
    ILastVisibleItemFinder, IFirstVisibleItemFinder {

    override fun findLastVisibleItemPosition(): Int =
        linearLayoutManager.findLastVisibleItemPosition()

    override fun findFirstVisibleItemPosition(): Int =
        linearLayoutManager.findFirstVisibleItemPosition()

}