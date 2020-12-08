package com.rosalynbm.shoestoreinventory

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.layout_navigation_drawer.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return

        navController = navHostFragment.navController
        navController.navigate(R.id.LoginFragment)

        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout,
            R.string.open_drawer, R.string.close_drawer)

        // Configure the drawer layout to add listener and show the hamburger icon on toolbar
        drawerToggle.isDrawerIndicatorEnabled = true
        // Set the drawer toggle as the DrawerListener
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        logout_btn.setOnClickListener {
            navController.navigate(R.id.LoginFragment)
            drawer_layout.closeDrawer(GravityCompat.START)
        }

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.logout_btn -> navController.navigate(R.id.LoginFragment)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * This is for when the user press back button,
     * we should check the navigationView: will be closed first and then the app
     *
     */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawer_layout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

}