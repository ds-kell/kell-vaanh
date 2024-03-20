package vn.com.kell.vaanh.ui.tmp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kell.com.example.vaanh.databinding.ItemTmpBinding

class TmpAdapter : RecyclerView.Adapter<TmpAdapter.Holder>() {

    private var listItem: List<TmpModel> = mutableListOf()

    inner class Holder(val view: ItemTmpBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {

            }
        }

        fun bind(model: TmpModel, position: Int) {
            view.apply {
                tvTmp.text = model.name
                executePendingBindings()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemTmpBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listItem[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<TmpModel>) {
        listItem = newList.toMutableList()
        notifyDataSetChanged()
    }
}