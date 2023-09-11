package vn.com.kell.vaanh.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import vn.com.vti.common.R
import vn.com.kell.vaanh.adapter.binding.BindingArrayAdapter
import vn.com.kell.vaanh.adapter.binding.BindingHolder
import vn.com.vti.common.databinding.ItemSuggestInputBinding
import vn.com.vti.common.extension.removeAllItemDecorations
import vn.com.vti.common.text.number.NumberConverter
import vn.com.vti.common.ui.decorator.MarginGridDecorator


class SuggestInputAdapter<T : Number>(private val formatter: NumberConverter<T>) :
    BindingArrayAdapter<T>() {
    override fun onCreateHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<out ViewDataBinding, T> = Holder(parent)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.removeAllItemDecorations()
        val horz =
            recyclerView.resources.getDimensionPixelSize(vn.com.vti.common.R.dimen.space_xsmall)
        val vert =
            recyclerView.resources.getDimensionPixelSize(vn.com.vti.common.R.dimen.space_xsmall)
        recyclerView.addItemDecoration(MarginGridDecorator(vert, horz, vert, vert))
    }

    private inner class Holder(parent: ViewGroup) :
        BindingHolder<ItemSuggestInputBinding, T>(
            parent, R.layout.item_suggest_input
        ) {

        override fun onCreate() {
            super.onCreate()
            registerRootViewItemClickEvent(mItemClickListener)
        }

        override fun onBind(position: Int, model: T?) {
            super.onBind(position, model)
            binder.apply {
                model?.let {
                    label = formatter.prettify(it)
                }
                executePendingBindings()
            }
        }
    }
}