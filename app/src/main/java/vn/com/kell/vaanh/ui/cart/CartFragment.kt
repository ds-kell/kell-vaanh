package vn.com.kell.vaanh.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentCartBinding
import vn.com.kell.vaanh.ui.cart.adapter.CartAdapter

@AndroidEntryPoint
class CartFragment : Fragment() {
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCartBinding.inflate(layoutInflater).apply {
            CartAdapter().also { adapter ->
                rcvItemInCart.adapter = adapter
                rcvItemInCart.layoutManager =
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
        }.root
    }
}
/*
*   btnRun.setOnClickListener {
                val animation: Animation =
                    AnimationUtils.loadAnimation(context, kell.com.example.vaanh.R.anim.fade_in)
                imgMommy.startAnimation(animation)
            }*/