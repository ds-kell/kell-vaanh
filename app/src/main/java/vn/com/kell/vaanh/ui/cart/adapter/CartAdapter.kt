package vn.com.kell.vaanh.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.LayoutItemInCartBinding
import vn.com.kell.vaanh.binding.VaAnhNumberBinding
import vn.com.kell.vaanh.model.ProductDTO
import vn.com.kell.vaanh.model.ProductInCart

class CartAdapter : RecyclerView.Adapter<CartAdapter.Holder>() {
    private var listItem: List<ProductInCart> = mutableListOf()

    inner class Holder(val view: LayoutItemInCartBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {

            }
        }

        fun bind(model: ProductInCart, position: Int) {
            view.apply {
                val selectQuantity = VaAnhNumberBinding()
                layoutPickQuantity.numberBinding = selectQuantity
                Glide.with(itemView.context)
                    .load(model.productDTO.images[0].url)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .centerCrop()
                    .into(imgProduct)

                tvProductName.text = model.productDTO.name
                executePendingBindings()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutItemInCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listItem[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<ProductDTO>) {
        val tmp = newList.map {
            ProductInCart(it)
        }
        listItem = tmp.toMutableList()
        notifyDataSetChanged()
    }
}