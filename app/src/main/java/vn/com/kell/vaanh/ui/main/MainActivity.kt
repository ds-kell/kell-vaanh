package vn.com.kell.vaanh.ui.main

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.FloatingWindow
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kell.com.example.vaanh.R
import kell.com.example.vaanh.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    companion object {
        val idsRoot = listOf(
            R.id.fragment_home,
            R.id.fragment_cart,
            R.id.fragment_user_profile,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).apply {
            navController = getNavController()
            navBottom.setupWithNavController(navController)

            navController.let { controller ->
                controller.addOnDestinationChangedListener { controller, destination, _ ->
                    if (destination is FloatingWindow) return@addOnDestinationChangedListener
                    val visible =
                        idsRoot.firstOrNull { it == controller.currentDestination?.id } != null
                    val stateShow = if (visible) View.VISIBLE else View.GONE
                    layoutCollapsingToolbar.visibility = stateShow
                    layoutToolbar.visibility = stateShow
                    navBottom.visibility = stateShow


                    layoutToolbar2.apply {
                        layoutCt.visibility = if (visible) View.GONE else View.VISIBLE
                        layoutUserAction.visibility = if (visible) View.GONE else View.VISIBLE
                    }

                    if (controller.currentDestination?.id == R.id.fragment_user_profile || controller.currentDestination?.id == R.id.fragment_cart) {
                        layoutToolbar.visibility = View.GONE
                    }
                }
            }
            layoutToolbar2.apply {
                imgArrow.setOnClickListener {
                    hideKeyboard()
                    onBackPressedDispatcher.onBackPressed()
                    layoutSearch.apply {
                        edtSearchContent.clearText()
                    }
                }
            }
        }.root)

    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus
        if (currentFocusView != null) {
            imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
            currentFocusView.clearFocus()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }


    fun headerStatus() {

    }

    fun footerStatus() {

    }

    fun provideNavHostId(): Int = R.id.nav_main_host_fragment
    fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_main_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }

    fun FragmentActivity.dispatchBackPressed() = onBackPressedDispatcher.onBackPressed()


}

fun EditText.clearText() {
    this.text.clear()
}