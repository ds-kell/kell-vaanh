package kell.com.example.vaanh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kell.com.example.vaanh.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var bindingFragment: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentHomeBinding.inflate(layoutInflater)
        return bindingFragment.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingFragment.btnHomeToFrag1.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogIn, null)
        }
        bindingFragment.btnHomeToFrag2.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.fragmentSignUp, null)
        )
    }
}