package vn.com.kell.vaanh.ui.home.contract

import kotlinx.coroutines.flow.StateFlow
import vn.com.kell.vaanh.model.Product

interface HomeContract {
    fun getProducts(): StateFlow<List<Product>>
}