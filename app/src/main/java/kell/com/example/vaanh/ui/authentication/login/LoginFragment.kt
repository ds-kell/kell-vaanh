package kell.com.example.vaanh.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.FragmentLoginBinding
import kell.com.example.vaanh.ui.authentication.login.contract.LogInViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LogInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@LoginFragment
            vm = viewModel
            tvSignup.setOnClickListener {
                findNavController().navigate(R.id.fragment_sign_up)
            }
        }.root
    }
}
//viewModel.onSubmit(toHome = {
//                    findNavController().navigate(LoginFragmentDirections.toHome(it))
//                })
