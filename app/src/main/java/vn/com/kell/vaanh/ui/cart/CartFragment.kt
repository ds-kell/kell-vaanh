package vn.com.kell.vaanh.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentCartBinding

@AndroidEntryPoint
class CartFragment : Fragment() {
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCartBinding.inflate(layoutInflater).apply {
            btnRun.setOnClickListener {
                val animation: Animation =
                    AnimationUtils.loadAnimation(context, kell.com.example.vaanh.R.anim.fade_in)
                imgMommy.startAnimation(animation)
            }
        }.root
    }
}