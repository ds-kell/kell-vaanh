package kell.com.example.vaanh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kell.com.example.vaanh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switchLayout()

    }

    private fun switchLayout() {
        val sub1 = FragmentLogIn()
        val sub2 = FragmentSignUp()
        var ok = 0
        binding.btnSwitch.setOnClickListener {
            ok = if (ok == 0) {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, sub1)
                    addToBackStack(null)
                    commit()
                }
                1

            } else {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, sub2)
                    addToBackStack(null)
                    commit()
                }
                0
            }
        }
    }

}
