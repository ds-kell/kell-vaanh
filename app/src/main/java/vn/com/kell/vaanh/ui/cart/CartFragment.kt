package vn.com.kell.vaanh.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentCartBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.ui.cart.adapter.CartAdapter
import vn.com.kell.vaanh.ui.home.contract.HomeViewModel

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCartBinding.inflate(layoutInflater).apply {
            val adapter = CartAdapter()
            rcvItemInCart.adapter = adapter
            rcvItemInCart.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProducts().collectLatest {
                    adapter.updateData(it)
                }
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