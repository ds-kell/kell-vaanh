package vn.com.kell.vaanh.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.ui.home.adapter.HomeAdapter
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
            val adapter = HomeAdapter().also { adapter ->
                rcvProducts.adapter = adapter
                rcvProducts.layoutManager = GridLayoutManager(context, 3)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProducts().collectLatest {
                    adapter.updateData(it)
                }
            }
        }.root
    }
}