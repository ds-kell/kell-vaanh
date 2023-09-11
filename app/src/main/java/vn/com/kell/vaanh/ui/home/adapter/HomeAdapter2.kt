package vn.com.kell.vaanh.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kell.com.example.vaanh.databinding.LayoutImageBinding
import vn.com.kell.vaanh.model.Product

class HomeAdapter2 : RecyclerView.Adapter<HomeAdapter2.ViewHolder>() {
    private var listItem: List<Product> = mutableListOf()

    inner class ViewHolder(val view: LayoutImageBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {

            }
        }

        fun bind(model: Product, position: Int) {
            view.apply {
//                Glide.with(itemView.context)
//                    .load(model.images[0].url)
//                    .override(400, 500)
//                    .centerCrop()
//                    .into(imgProduct)
//                product = model
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Product>) {
        listItem = newList.toMutableList()
        notifyDataSetChanged()
    }
}