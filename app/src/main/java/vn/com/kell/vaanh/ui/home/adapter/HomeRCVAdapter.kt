package vn.com.kell.vaanh.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kell.com.example.vaanh.databinding.LayoutProductBinding
import vn.com.kell.vaanh.model.ProductDTO

class HomeRCVAdapter(private val onItemClick: (ProductDTO) -> Unit) :
    RecyclerView.Adapter<HomeRCVAdapter.Holder>() {
    private var listItem: List<ProductDTO> = mutableListOf()

    inner class Holder(val view: LayoutProductBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {
                onItemClick(listItem[absoluteAdapterPosition])
            }
        }

        fun bind(model: ProductDTO, position: Int) {
            view.apply {
                Glide.with(itemView.context)
                    .load(model.images[0].url)
                    .override(400, 500)
                    .centerCrop()
                    .into(imgProduct)

                product = model
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutProductBinding.inflate(
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