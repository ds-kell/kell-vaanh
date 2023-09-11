package vn.com.kell.vaanh.adapter.tree

import vn.com.kell.vaanh.adapter.binding.BindingAdapter

@Suppress("MemberVisibilityCanBePrivate")
abstract class ExpandableMenuAdapter<T> : BindingAdapter<Node<T>>() {

    private val flattenNode: MutableList<Node<T>> = mutableListOf()

    override fun getItemCount() = flattenNode.size

    override fun getItem(position: Int) = flattenNode[position]

    protected fun expand(index: Int) {
        val node = flattenNode[index]
        if (node.type != Node.GROUP) {
            return
        }
        node.run {
            getFlatChildNode()?.let {
                flattenNode.addAll(index + 1, it)
                notifyItemRangeInserted(index + 1, it.size)
            }
            setFlatChildNode(null)
            notifyItemChanged(index)
        }
    }

    protected fun collapse(index: Int) {
        val node = flattenNode[index]
        if (node.type != Node.GROUP || index == itemCount - 1) {
            return
        }
        val level = node.level
        val start = index + 1
        val end = (start until itemCount).firstOrNull {
            level >= flattenNode[it].level
        } ?: itemCount
        flattenNode.subList(start, end).let {
            if (it.isNotEmpty()) {
                node.setFlatChildNode(it)
                val count = it.size
                it.clear()
                notifyItemRangeRemoved(start, count)
                notifyItemChanged(index)
            }
        }
    }

    protected fun toggle(index: Int) {
        flattenNode[index].let {
            if (it.type != Node.GROUP) {
                return
            }
            if (it.isCollapsed()) {
                expand(index)
            } else {
                collapse(index)
            }
        }
    }

    fun setNodes(nodes: List<Node<T>>) {
        if (itemCount > 0) notifyItemRangeRemoved(0, itemCount)
        flattenNode.clear()
        flattenNode.addAll(nodes)
        if (itemCount > 0) notifyItemRangeInserted(0, itemCount)
    }

    fun getData() = flattenNode

    fun findParentNodePosition(position: Int, nodeLevel: Int): Int {
        return (position downTo 0).firstOrNull {
            getItem(position).level == nodeLevel
        } ?: -1
    }

    fun findParentNode(position: Int, nodeLevel: Int): Node<T>? {
        return (position downTo 0).firstNotNullOfOrNull { index ->
            getItem(index).let {
                if (it.level == nodeLevel) it
                else null
            }
        }
    }

    override fun clear() {
        super.clear()
        val size = flattenNode.size
        flattenNode.clear()
        if (size > 0)
            notifyItemRangeRemoved(0, size)
    }
}