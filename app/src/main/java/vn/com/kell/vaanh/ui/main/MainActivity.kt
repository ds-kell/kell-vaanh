package vn.com.kell.vaanh.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.appbar.CollapsingToolbarLayout
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.R


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val cheeseName = intent.getStringExtra(EXTRA_NAME)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.mat_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = "Kafka"


        var isHeaderVisible = true
//        val customHeaderLayout = findViewById<View>(R.id.layout_header)
//        val scrollView = findViewById<View>(R.id.scrollable_view)

//        scrollView.viewTreeObserver.addOnScrollChangedListener {
//            if (scrollView.scrollY == 0 && !isHeaderVisible) {
//                customHeaderLayout.animate().translationY(0f)
//                isHeaderVisible = true
//            } else if (scrollView.scrollY > 0 && isHeaderVisible) {
//                customHeaderLayout.animate().translationY(-customHeaderLayout.height.toFloat())
//                isHeaderVisible = false
//            }
//        }
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_main_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val navMenuBottom = findViewById<BottomNavigationView>(R.id.nav_menu_bottom)
//        navMenuBottom.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}
