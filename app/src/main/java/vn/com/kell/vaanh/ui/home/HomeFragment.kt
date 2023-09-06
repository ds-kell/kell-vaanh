package vn.com.kell.vaanh.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //    private val args: HomeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(layoutInflater).apply {
//            username = args.username
            btnViewProfile.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.toFragmentUserProfile())
            }
        }.root
    }
}