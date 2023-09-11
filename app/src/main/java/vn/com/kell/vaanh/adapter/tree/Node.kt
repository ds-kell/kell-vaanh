package vn.com.kell.vaanh.adapter.tree

class Node<T>(
    val type: Int = GROUP,
    val level: Int = 0,
    val data: T,
    private var childNode: List<Node<T>>? = null,
) {

    fun getFlatChildNode(): List<Node<T>>? = childNode

    fun setFlatChildNode(nodes: List<Node<T>>?) {
        this.childNode = nodes
    }

    fun isCollapsed() = getFlatChildNode()?.isNotEmpty() ?: false

    companion object {
        const val GROUP = 0
        const val ITEM = 1
    }

}