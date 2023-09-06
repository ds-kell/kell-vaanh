package vn.com.kell.vaanh.ui.authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.FragmentSignUpBinding
import vn.com.kell.vaanh.ui.authentication.signup.contract.SignUpViewModel

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSignUpBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@SignUpFragment
            vm = viewModel
            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.fragment_login)
            }
            btnSubmit.setOnClickListener {
                viewModel.onSubmit(toHome = {
                })
            }
            viewModel.doSomething()
        }.root
    }
}
