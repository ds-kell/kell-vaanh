package vn.com.kell.vaanh.ui.tmp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.FragmentTmpBinding
import vn.com.kell.vaanh._no_run.setUpSwipe

@AndroidEntryPoint
class TmpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTmpBinding.inflate(layoutInflater).apply {

            recTmp.adapter = TmpAdapter().also {adapter ->
                adapter.updateData(listOf(
                    TmpModel("Kell"),
                    TmpModel("Lys"),
                    TmpModel("DelOnR"),
                    TmpModel("Yahalo"),
                ))
            }
            recTmp.setUpSwipe()
        }.root
    }
}
