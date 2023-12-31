package vn.com.kell.vaanh.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.MainGraphDirections
import kell.com.example.vaanh.databinding.FragmentProductDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.model.Image
import vn.com.kell.vaanh.model.ProductDTO
import vn.com.kell.vaanh.ui.home.adapter.HomeRCVAdapter
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
            viewModel.getBrandId(args.brandId)
            val pagerAdapter = ProductViewPagerAdapter()
            vpgProductImages.adapter = pagerAdapter
            vpgProductImages.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//            val currentPageIndex = 1
//            vpgProductImages.currentItem = currentPageIndex
//            vpgProductImages.registerOnPageChangeCallback(
//                object : ViewPager2.OnPageChangeCallback() {
//                    override fun onPageSelected(position: Int) {
//                        super.onPageSelected(position)
//                    }
//                }
//            )

            val rcvSnapAdapter = ProductSnapHelperAdapter(selectItem = { model, position ->
                viewModel.setImageSelection(model)
                Log.d("kell-log", "${viewModel.getImageSelection.value}")
                vpgProductImages.currentItem = position
            })
            rcvProductImages.adapter = rcvSnapAdapter
            rcvProductImages.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            val centerSnapHelperAdapter = LinearSnapHelper()
            centerSnapHelperAdapter.attachToRecyclerView(rcvProductImages)

            val rcvAdapter = HomeRCVAdapter(onItemClick = { product ->
                findNavController().navigate(
                    MainGraphDirections.toFragmentProductDetail(
                        product.id, product.brand.id
                    )
                )
            }).also { adapter ->
                rcvProductOfBrand.adapter = adapter
                rcvProductOfBrand.layoutManager = GridLayoutManager(context, 2)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProductDetail().collectLatest { dtos ->
                    val images = dtos.flatMap { it.productDTO.images }
                    val tmp: MutableList<Image> = mutableListOf()
                    tmp.addAll(images)
                    tmp.addAll(images)
                    tmp.addAll(images)
                    pagerAdapter.updateData(tmp)
                    rcvSnapAdapter.updateData(tmp)
                    layoutInfoProduct.productFirst = dtos.firstOrNull()
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getProductOfBrand().collectLatest {
                    val tmp = mutableListOf<ProductDTO>()
                    tmp.addAll(it)
                    tmp.addAll(it)
                    tmp.addAll(it)
                    tmp.addAll(it)
                    tmp.addAll(it)
                    tmp.addAll(it)
                    tmp.addAll(it)
                    rcvAdapter.updateData(tmp)
                }
            }
            layoutUserAction.apply {
                tvBuyNow.setOnClickListener {

                }
            }
        }.root
    }
}

/*
*  (activity as MainActivity).apply {

        }*/