package vn.com.kell.vaanh.ui.media

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kell.com.example.vaanh.databinding.FragmentMediaBinding

class MediaFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMediaBinding.inflate(layoutInflater).apply {
            mediaPlayer = MediaPlayer()

            val musicFilePath = "/sdcard/music/sample.mp3"
            try {
                mediaPlayer?.setDataSource(musicFilePath)
                mediaPlayer?.prepare()
                mediaPlayer?.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            btnPlay.setOnClickListener { onPlayButtonClicked() }
            btnStop.setOnClickListener { onStopButtonClicked() }
        }.root
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun onPlayButtonClicked() {
        mediaPlayer?.start()
    }

    private fun onStopButtonClicked() {
        mediaPlayer?.stop()
        mediaPlayer?.prepare()
    }

}
