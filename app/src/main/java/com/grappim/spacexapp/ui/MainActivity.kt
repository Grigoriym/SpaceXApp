package com.grappim.spacexapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  private lateinit var navController: NavController
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var switcher: SwitchCompat

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    setupNavigation()
    setupThemeChange()
  }

  private fun setupThemeChange() {
    val menu = navigationView.menu
    val menuItem = menu.findItem(R.id.nav_switch_theme)
    val actionView = menuItem.actionView
    switcher = actionView.findViewById(R.id.drawerSwitch)
    switcher.isChecked = delegate.localNightMode != AppCompatDelegate.MODE_NIGHT_NO
    switcher.setOnCheckedChangeListener { _, isChecked ->
      run {
        if (!isChecked) {
          delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        } else {
          delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }
      }
    }
  }

  private fun setupNavigation() {
    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.capsuleFragment,
        R.id.rocketFragment,
        R.id.shipsFragment,
        R.id.coreFragment
      ), drawerLayout
    )

    navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    setupActionBarWithNavController(navController, appBarConfiguration)
    navigationView.setupWithNavController(navController)
    navigationView.setNavigationItemSelectedListener(this)
  }

  override fun onSupportNavigateUp() =
    findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)

  override fun onNavigationItemSelected(p0: MenuItem): Boolean {
    p0.isChecked = true
    drawerLayout.closeDrawers()
    return displaySelectedScreen(p0.itemId)
  }

  private fun displaySelectedScreen(itemId: Int): Boolean {
    when (itemId) {
      R.id.nav_capsules -> navController.navigate(R.id.capsuleFragment)
      R.id.nav_rockets -> navController.navigate(R.id.rocketFragment)
      R.id.nav_ships -> navController.navigate(R.id.shipsFragment)
      R.id.nav_cores -> navController.navigate(R.id.coreFragment)
      R.id.nav_switch_theme -> {
        switcher.isChecked = !switcher.isChecked
        return false
      }
    }
    drawerLayout.closeDrawer(GravityCompat.START)
    return true
  }

  override fun onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }
}
