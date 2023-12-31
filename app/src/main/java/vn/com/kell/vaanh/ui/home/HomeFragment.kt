package vn.com.kell.vaanh.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.MainGraphDirections
import kell.com.example.vaanh.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.ui.home.adapter.CategoryHomeAdapter
import vn.com.kell.vaanh.ui.home.adapter.HomeRCVAdapter
import vn.com.kell.vaanh.ui.home.contract.HomeViewModel


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(layoutInflater).apply {
            val productAdapter = HomeRCVAdapter(onItemClick = { product ->
                findNavController().navigate(
                    MainGraphDirections.toFragmentProductDetail(
                        product.id, product.brand.id
                    )
                )
            }).also { adapter ->
                rcvProducts.adapter = adapter
                rcvProducts.layoutManager = GridLayoutManager(context, 2)
            }
            val categoryAdapter = CategoryHomeAdapter().also { adapter2 ->
                rcvHomeCategory.adapter = adapter2
                rcvHomeCategory.layoutManager =
                    GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProducts().collectLatest {
                    productAdapter.updateData(it)
                }
            }

        }.root
    }
}