package vn.com.kell.vaanh.adapter.binding

import android.view.View
import androidx.databinding.ViewDataBinding
import vn.com.kell.vaanh.adapter.BaseAdapter
import vn.com.kell.vaanh.adapter.OnItemClick

abstract class BindingAdapter<M> :
    BaseAdapter<BindingHolder<out ViewDataBinding, M>>() {

    protected var mItemClickListener: OnItemClick<M>? = null

    override fun onBindViewHolder(
        holder: BindingHolder<out ViewDataBinding, M>,
        position: Int
    ) = holder.onBind(position, getItem(position))

    protected fun setRootViewItemClick(target: BindingHolder<out ViewDataBinding, M>) {
        target.registerRootViewItemClickEvent(mItemClickListener)
    }

    fun setItemClickListener(itemClick: OnItemClick<M>?) {
        mItemClickListener = itemClick
    }

    fun setItemClickListener(listener: (position: Int, view: View?, model: M?) -> Unit) {
        mItemClickListener = OnItemClick(listener)
    }

    abstract fun getItem(position: Int): M?
}