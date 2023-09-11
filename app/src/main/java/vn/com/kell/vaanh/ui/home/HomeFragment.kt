package vn.com.kell.vaanh.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.ui.home.adapter.HomeAdapter
import vn.com.kell.vaanh.ui.home.adapter.HomeAdapter2
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
                rcvProducts.layoutManager = GridLayoutManager(context, 2)
            }
            val adapter2 = HomeAdapter2().also { adapter ->
                rcvProductsX.adapter = adapter
                rcvProductsX.layoutManager =
                    GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProducts().collectLatest {
                    adapter.updateData(it)
                    adapter2.updateData(it)
                }
            }


//            val coordinatorLayout = findViewById(R.id.main_content) as CoordinatorLayout
//            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<View>(rcvProducts)
//
//            layoutCoor.setOnClickListener(View.OnClickListener {
//                if (behavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
//                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
//                } else {
//                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
//                }
//            })
        }.root
    }
}