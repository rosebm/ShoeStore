package com.rosalynbm.shoestoreinventory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.host_activity.*

class HostActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity)
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return

        val navController = navHostFragment.navController
        navController.navigate(R.id.LoginFragment)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_setting -> {
                    navController.navigate(R.id.LoginFragment)
                    true
                }
                else -> {
                    navController.navigate(R.id.LoginFragment)
                    true
                }
            }
        }
    }

}