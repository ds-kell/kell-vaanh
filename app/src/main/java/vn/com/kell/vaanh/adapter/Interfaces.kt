package vn.com.kell.vaanh.adapter

import android.view.View

interface OnDataChangedListener {

    fun onDataSetEmpty()

    fun onDataSetFilled()
}

fun interface OnItemClick<T> {

    fun onItemClick(position: Int, view: View?, t: T?)

}