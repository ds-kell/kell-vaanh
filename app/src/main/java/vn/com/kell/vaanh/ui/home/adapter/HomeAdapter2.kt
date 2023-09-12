package vn.com.kell.vaanh.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kell.com.example.vaanh.databinding.LayoutCategoryItemBinding
import vn.com.kell.vaanh.model.ProductDTO

class HomeAdapter2 : RecyclerView.Adapter<HomeAdapter2.Holder>() {
    private var listItem: List<ProductDTO> = mutableListOf()

    inner class Holder(val view: LayoutCategoryItemBinding) :
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
        LayoutCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listItem[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<ProductDTO>) {
        listItem = newList.toMutableList()
        notifyDataSetChanged()
    }
}