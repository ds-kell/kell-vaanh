package vn.com.kell.vaanh.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kell.com.example.vaanh.databinding.LayoutItemInCartBinding
import vn.com.kell.vaanh.model.ProductDTO

class CartAdapter : RecyclerView.Adapter<CartAdapter.Holder>() {
    private var listItem: List<ProductDTO> = mutableListOf()

    inner class Holder(val view: LayoutItemInCartBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {

            }
        }

        fun bind(model: ProductDTO, position: Int) {
            view.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutItemInCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(listItem[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<ProductDTO>) {
        listItem = newList.toMutableList()
        notifyDataSetChanged()
    }
}