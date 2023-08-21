package kell.com.example.vaanh.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivityMainBinding.inflate(layoutInflater).apply {

            }.root
        )
    }
}
