package kell.com.example.vaanh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kell.com.example.vaanh.databinding.FragmentSignUpBinding

//class FragmentSignUp : Fragment(R.layout.fragment_sign_up) {
//
//}
class FragmentSignUp : Fragment() {
    private lateinit var bindingFragment: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentSignUpBinding.inflate(layoutInflater)

//        val dividerItemDecoration = DividerItemDecoration(bindingFragment.root.context, DividerItemDecoration.VERTICAL)
//        bindingFragment.rcvFg.addItemDecoration(dividerItemDecoration)
        return bindingFragment.root
    }
}