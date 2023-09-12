package vn.com.kell.vaanh.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentProductDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.ui.product.adapter.ProductSnapHelperAdapter
import vn.com.kell.vaanh.ui.product.adapter.ProductViewPagerAdapter
import vn.com.kell.vaanh.ui.product.contract.ProductDetailViewModel

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val viewModel: ProductDetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProductDetailBinding.inflate(layoutInflater).apply {
            val productId = args.productId
            viewModel.getProductId(productId)
            val pagerAdapter = ProductViewPagerAdapter()
            viewPagerProductImages.adapter = pagerAdapter
            viewPagerProductImages.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            val currentPageIndex = 1
            viewPagerProductImages.currentItem = currentPageIndex
            viewPagerProductImages.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                }
            )

            val helperAdapter = ProductSnapHelperAdapter()
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProductDetail().collectLatest { dtos ->
                    val images = dtos.flatMap { it.productDTO.images }
                    pagerAdapter.updateData(images)
                }
            }
        }.root
    }
}