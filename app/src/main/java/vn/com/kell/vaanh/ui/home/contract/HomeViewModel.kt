package vn.com.kell.vaanh.ui.home.contract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.interator.GetProductUseCase
import vn.com.kell.vaanh.model.Product
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
) : ViewModel(), HomeContract {
    private val stateProduct: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    override fun getProducts(): StateFlow<List<Product>> = stateProduct

    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch {
            val response = getProductUseCase.execute()
//            response.forEach { p ->
//                if (p.images.isNotEmpty()) {
//                    p.images[0].url =
//                        "https://firebasestorage.googleapis.com/v0/b/wibu-web-app.appspot.com/o/images%2F2eab4d35-29a2-41fa-aab1-17eeecc96d1390504261_p0.png?alt=media&token=88ab18ea-3b67-414c-9987-4c60a94424a7"
//                }
//            }
            val tmp = mutableListOf<Product>()
            tmp.addAll(response)
            tmp.addAll(response)
            tmp.addAll(response)
            stateProduct.value = tmp
        }
    }
}
