package vn.com.kell.vaanh.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentAccountProfileBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.com.kell.vaanh.ui.user_profile.contract.AccountProfileViewModel

@AndroidEntryPoint
class AccountProfileFragment : Fragment() {
    private val viewModel: AccountProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAccountProfileBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@AccountProfileFragment
            vm = viewModel
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getAccountProfile().collectLatest {
                    accountProfile = it
                    if (it != null) {
                        Glide.with(this@AccountProfileFragment)
                            .load("https://firebasestorage.googleapis.com/v0/b/wibu-web-app.appspot.com/o/images%2F2759ce1d-0863-4ef4-b0e2-e5904413e6c086182186_p0.png?alt=media&token=c85a4075-a319-46ea-bf9a-5a50befbfb9a")
                            .centerCrop()
                            .into(layoutHeaderAccount.imgUserAvt)
                    }

                }
            }
//            btnUpdateProfile.setOnClickListener {
//                viewModel.statusUpdate()
//            }
        }.root
    }
}
/**
.override(400, 500)
 * */
