package kell.com.example.vaanh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kell.com.example.vaanh.databinding.FragmentLogInBinding

class FragmentLogIn : Fragment() {
    private lateinit var bindingFragment: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)
        bindingFragment.message = "Kell"
        // Gắn viewModel cho Binding
        bindingFragment.viewModel = this

        return bindingFragment.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingFragment.btnFrag1ToFrag2.setOnClickListener {
            findNavController().navigate(R.id.fragmentSignUp, null)
        }
        bindingFragment.btnBackToHome.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.homeFragment, null)
        )
    }
    fun onClickChange() {
        bindingFragment.message = "Lisana"
    }
}