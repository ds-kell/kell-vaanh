package kell.com.example.vaanh.ui.user_profile

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
import kell.com.example.vaanh.ui.user_profile.contract.AccountProfileViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
                            .load(it.avatarUrl)
                            .override(400, 500)
                            .centerCrop()
                            .into(imgAvatar)
                    }

                }
            }
            btnUpdateProfile.setOnClickListener {
                viewModel.statusUpdate()
            }
        }.root
    }

}

