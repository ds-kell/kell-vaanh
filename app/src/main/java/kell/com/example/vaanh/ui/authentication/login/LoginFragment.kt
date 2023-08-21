package kell.com.example.vaanh.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kell.com.example.vaanh.databinding.FragmentLoginBinding
import kell.com.example.vaanh.ui.authentication.login.contract.LoginViewModel

class LoginFragment : Fragment() {
    private val viewModel = LoginViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLoginBinding.inflate(layoutInflater).apply {
            vm = viewModel
        }.root
    }
}