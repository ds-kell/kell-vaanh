package kell.com.example.vaanh.ui.authentication.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.FragmentLoginBinding
import kell.com.example.vaanh.ui.authentication.login.contract.LogInViewModel
import kell.com.example.vaanh.ui.main.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getStateToActivity().collectLatest {
                    if (it) {
                        viewModel.setStateToActivity(false)
                        val mainIntent = Intent(requireContext(), MainActivity::class.java)
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(mainIntent)
                    }
                }
            }
        }.root
    }
}
