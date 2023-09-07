package vn.com.kell.vaanh._no_run

import androidx.fragment.app.Fragment

class MyFragment : Fragment() {

//    private val viewModel: MyViewModel by viewModel

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Create a new coroutine in the lifecycleScope
//        viewLifecycleOwner.lifecycleScope.launch {
//            // repeatOnLifecycle launches the block in a new coroutine every time the
//            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                // Trigger the flow and start listening for values.
//                // This happens when lifecycle is STARTED and stops
//                // collecting when the lifecycle is STOPPED
//                viewModel.someDataFlow.collect {
//                    // Process item
//                }
//            }
//        }
//    }
}
/*Đúng vậy,
`viewLifecycleOwner` đại diện cho `MyFragment`
`viewLifecycleOwner` là một thuộc tính của lớp `Fragment`, được sử dụng để truy cập đến đối tượng `LifecycleOwner` liên quan đến view của mảnh.
Đối tượng này cho phép theo dõi vòng đời của view và thực hiện các hành động phù hợp khi vòng đời thay đổi.


`viewLifecycleOwner.lifecycleScope.launch` được sử dụng để tạo một coroutine mới trong phạm vi vòng đời của view.
Coroutine này sẽ chạy khi view được tạo ra và sẽ bị hủy khi view bị hủy.
Trong coroutine, phương thức `repeatOnLifecycle` được sử dụng để khởi chạy một khối lệnh mới mỗi khi vòng đời của view ở trạng thái `STARTED`
hoặc cao hơn và hủy nó khi vòng đời ở trạng thái `STOPPED`.
Điều này cho phép theo dõi các giá trị từ luồng dữ liệu `viewModel.someDataFlow` và xử l    ý chúng khi view đang hoạt động.

*/