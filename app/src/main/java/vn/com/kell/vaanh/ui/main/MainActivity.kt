package vn.com.kell.vaanh.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_main_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        ActivityMainBinding.inflate(layoutInflater).apply {
            bottomAppbar.apply {
                icHome = AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_home)
                icCart = AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_cart)
                icNotification =
                    AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_notifications)
                icAccount =
                    AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_account_circle)

                homeLabel = getString(R.string.label_home)
                cartLabel = getString(R.string.label_cart)
                notificationLabel = getString(R.string.label_notification)
                accountLabel = getString(R.string.label_account)
            }
        }
    }

//    fun onNavItemClick(view: View) {
//        if (::navController.isInitialized) {
//            when (view.id) {
//                R.id.nav_home -> {
//                }
//
//                R.id.nav_cart -> {
//                }
//
//                R.id.nav_notification -> {
//                }
//
//                R.id.nav_account -> {
//                    navController.navigate(R.id.fragment_user_profile)
//                }
//
//                else -> {
//                }
//            }
//        }
//    }
}
