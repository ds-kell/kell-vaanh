package vn.com.vti.common.adapter.widget

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import vn.com.vti.common.R
import vn.com.kell.vaanh.adapter.binding.BindingHolder
import vn.com.vti.common.databinding.ItemSimpleDropdownBinding

@Suppress("unused")
abstract class DropdownAdapter<MODEL> : android.widget.BaseAdapter(), Filterable {

    final override fun getItem(position: Int) = getLabel(position)

    override fun getItemId(position: Int) = position.toLong()

    @Suppress("UNCHECKED_CAST")
    final override fun getDropDownView(
        position: Int,
        _convertView: View?,
        parent: ViewGroup,
    ): View {
        val holder: BindingHolder<*, MODEL?>
        var convertView = _convertView
        if (convertView == null) {
            holder = onCreateDropdownViewBinding(parent)
            convertView = holder.itemView
            convertView.tag = holder
        } else {
            holder = convertView.tag as BindingHolder<*, MODEL?>
        }
        holder.onBind(position, getModel(position))
        return convertView
    }

    @Suppress("UNCHECKED_CAST")
    final override fun getView(
        position: Int,
        _convertView: View?,
        parent: ViewGroup,
    ): View {
        val holder: BindingHolder<*, MODEL?>
        var convertView = _convertView
        if (convertView == null) {
            holder = onCreateViewBinding(parent)
            convertView = holder.itemView
            convertView.tag = holder
        } else {
            holder = convertView.tag as BindingHolder<*, MODEL?>
        }
        holder.onBind(position, getModel(position))
        return convertView
    }

    protected open fun onCreateDropdownViewBinding(parent: ViewGroup): BindingHolder<out ViewDataBinding, MODEL?> =
        onCreateViewBinding(parent)

    protected abstract fun onCreateViewBinding(parent: ViewGroup): BindingHolder<out ViewDataBinding, MODEL?>

    protected abstract fun getModel(position: Int): MODEL

    protected open fun getLabel(position: Int): String? = getModel(position).toString()

}

abstract class DropdownArrayAdapter<T>(
    data: List<T> = listOf(),
    filterPredicate: ((T, CharSequence) -> Boolean) = { _, _ -> true },
    private val onItemClick: ((position: Int, item: T) -> Unit)? = null,
) : DropdownAdapter<T>(), AdapterView.OnItemClickListener {

    private val mFilter = ArrayFilter(predicate = filterPredicate, onFiltered = {
        if (count == 0) notifyDataSetInvalidated()
        else notifyDataSetChanged()
    }).apply {
        original = data
    }

    fun setData(data: List<T>?) {
        mFilter.apply {
            original = data ?: emptyList()
        }
    }

    override fun getCount(): Int = mFilter.filteredSize

    override fun getModel(position: Int): T = mFilter[position]

    override fun getFilter(): Filter = mFilter

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onItemClick?.invoke(position, getModel(position))
    }
}

class ArrayFilter<T>(
    private val predicate: (T, CharSequence) -> Boolean,
    private val onFiltered: () -> Unit = {},
) : Filter() {

    private var lastConstraints: CharSequence? = null

    var original: List<T> = listOf()
        set(value) {
            field = value
            filter(lastConstraints)
        }

    private var filtered: List<T> = listOf()

    val filteredSize: Int
        get() = filtered.size

    val originalSize: Int
        get() = original.size

    operator fun get(index: Int): T = filtered[index]

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val result = FilterResults()
        lastConstraints = constraint
        if (constraint.isNullOrEmpty()) {
            result.count = original.size
            result.values = original.toList()
        } else {
            original.filter {
                predicate(it, constraint)
            }.let {
                result.count = it.size
                result.values = it
            }
        }
        return result
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        filtered = (results?.values?.let {
            @Suppress("UNCHECKED_CAST") (it as? List<T>)
        }) ?: listOf()
        onFiltered()
    }
}

open class DefaultDropdownAdapter<T>(
    data: List<T> = emptyList(),
    filterPredicate: ((T, CharSequence) -> Boolean) = { _, _ -> true },
    onItemClick: ((position: Int, item: T) -> Unit)? = null,
    private val holderSupplier: (parent: ViewGroup) -> BindingHolder<out ViewDataBinding, T?> = { parent ->
        DefaultHolder(
            parent
        ) { it?.toString() }
    },
    private val labelSupplier: (item: T?) -> String? = { it?.toString() },
) : DropdownArrayAdapter<T>(data, filterPredicate, onItemClick) {

    override fun onCreateViewBinding(parent: ViewGroup): BindingHolder<out ViewDataBinding, T?> =
        holderSupplier(parent)

    override fun getLabel(position: Int): String? = labelSupplier(getModel(position))
}

class DefaultHolder<T>(
    parent: ViewGroup,
    private val selectionPredicate: (T) -> Boolean = { false },
    private val writer: (T) -> String?,
) : BindingHolder<ItemSimpleDropdownBinding, T?>(parent, R.layout.item_simple_dropdown) {

    override fun onBind(position: Int, model: T?) {
        super.onBind(position, model)
        binder.apply {
            if (model != null) {
                selected = selectionPredicate(model)
                text = writer(model)
            } else {
                selected = false
                text = null
            }
            executePendingBindings()
        }
    }
}

object DropdownBindingAdapter {

    fun EditText.bindTextValue(
        text: CharSequence?,
    ) {
        val old = this.text?.toString()
        if (old != text) {
            if (isFocused) {
                val oldLength = old?.length ?: 0
                val lastSelection = selectionEnd
                setText(text)
                if (!text.isNullOrEmpty()) {
                    val length = text.length
                    if (oldLength == 0) {
                        setSelection(length)
                    } else {
                        val offset = oldLength - lastSelection
                        val newOffset = length - offset
                        setSelection(newOffset.coerceAtLeast(0))
                    }
                }
            } else {
                setText(text)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("autoCompleteTextValue")
    fun AutoCompleteTextView.bindAutoCompleteTextValue(
        text: CharSequence?,
    ) {
        setText(text, false)
        if (isFocused) {
            setSelection(text?.length ?: 0)
        }
    }

    @BindingAdapter(
        value = ["autoCompleteTextAdapter", "autoCompleteTextListener"], requireAll = true
    )
    @JvmStatic
    fun AutoCompleteTextView.bindAutoCompleteTextAdapter(
        adapter: DropdownAdapter<*>?,
        listener: AdapterView.OnItemClickListener?,
    ) {
        setAdapter(adapter)
        onItemClickListener = listener
    }
}