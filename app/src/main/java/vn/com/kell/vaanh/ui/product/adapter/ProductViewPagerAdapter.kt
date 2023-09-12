package vn.com.kell.vaanh.ui.product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.LayoutViewpagerItemBinding
import vn.com.kell.vaanh.model.Image

class ProductViewPagerAdapter :
    RecyclerView.Adapter<ProductViewPagerAdapter.Holder>() {
    private var listItem: List<Image> = emptyList()

    inner class Holder(val binding: LayoutViewpagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {
            Glide.with(binding.root.context)
                .load(image.url)
                .error(R.drawable.ic_baseline_error_outline_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgProductImg)
        }

    }

    override fun getItemCount(): Int = listItem.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewPagerAdapter.Holder = Holder(
        LayoutViewpagerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(listItem[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Image>) {
        listItem = newList.toMutableList()
        notifyDataSetChanged()
    }
}