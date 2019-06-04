package com.grappim.spacexapp.ui

import android.content.Context
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
import com.grappim.spacexapp.util.NIGHT_THEME_PREF_KEY
import com.grappim.spacexapp.util.THEME_PREFS
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  private lateinit var navController: NavController
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var switcher: SwitchCompat

  override fun onCreate(savedInstanceState: Bundle?) {
    Timber.d("MainActivity - onCreate")
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    setupNavigation()
    setupThemeChange()
  }

  private fun setupThemeChange() {
    Timber.d("mainActivity - setupThemeChange")
    val sp = getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)

    val menu = navigationView.menu
    val menuItem = menu.findItem(R.id.nav_switch_theme)
    val actionView = menuItem.actionView

    switcher = actionView.findViewById(R.id.drawerSwitch)
    switcher.isChecked = getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
      .getBoolean(NIGHT_THEME_PREF_KEY, false)
    delegate.localNightMode =
      if (!switcher.isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
    switcher.setOnCheckedChangeListener { _, isChecked ->
      run {
        if (!isChecked) {
          Timber.d("Switcher !isChecked")
          delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
          sp.edit().apply {
            putBoolean(NIGHT_THEME_PREF_KEY, false)
            apply()
          }
        } else {
          Timber.d("Switcher isChecked")
          delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
          sp.edit().apply {
            putBoolean(NIGHT_THEME_PREF_KEY, true)
            apply()
          }
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
        R.id.coreFragment,
        R.id.infoFragment,
        R.id.historyFragment,
        R.id.launchPadFragment,
        R.id.socialMediaFragment
      ), drawerLayout
    )

    setSupportActionBar(toolbar)
    navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    setupActionBarWithNavController(navController, appBarConfiguration)
    navigationView.setupWithNavController(navController)
    navigationView.setNavigationItemSelectedListener(this)
  }

  override fun onSupportNavigateUp() =
    findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    item.isChecked = true
    drawerLayout.closeDrawers()
    return displaySelectedScreen(item.itemId)
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
      R.id.nav_info -> navController.navigate(R.id.infoFragment)
      R.id.nav_history -> navController.navigate(R.id.historyFragment)
      R.id.nav_launch_pads -> navController.navigate(R.id.launchPadFragment)
      R.id.nav_social_media -> navController.navigate(R.id.socialMediaFragment)
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
