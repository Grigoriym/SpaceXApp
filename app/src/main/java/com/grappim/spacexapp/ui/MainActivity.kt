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
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.ui.launches.LaunchesFragmentDirections
import com.grappim.spacexapp.core.utils.PrefsManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
  MainActivityListener {

  private lateinit var navController: NavController
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var switcher: SwitchCompat

  @Inject
  lateinit var prefsManager: PrefsManager

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as SpaceXApplication).appComponent.inject(this)
    setupNightMode()
    Timber.d("MainActivity - onCreate")
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
    setupNavigation()
    setupSwitcher()
  }

  private fun setupNightMode() {
    AppCompatDelegate.setDefaultNightMode(
      if (!prefsManager.isNightThemeEnabled())
        AppCompatDelegate.MODE_NIGHT_YES
      else AppCompatDelegate.MODE_NIGHT_NO
    )
  }

  private fun setupSwitcher() {
    val menu = navigationView.menu
    val menuItem = menu.findItem(R.id.nav_switch_theme)
    val actionView = menuItem.actionView

    switcher = actionView.findViewById(R.id.drawerSwitch)
    switcher.isChecked = prefsManager.isNightThemeEnabled()
    switcher.setOnCheckedChangeListener { _, isChecked ->
      run {
        if (!isChecked) {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
          prefsManager.setNightTheme(false)
        } else {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
          prefsManager.setNightTheme(true)
        }
      }
    }
  }

  private fun setupNavigation() {
    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.capsuleFragment,
        R.id.getRocketsFragment,
        R.id.getShipsFragment,
        R.id.coreFragment,
        R.id.infoFragment,
        R.id.historyFragment,
        R.id.getLaunchPadsFragment,
        R.id.socialMediaFragment,
        R.id.launchesFragment
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

  override fun showLaunchDetails(model: LaunchModel) {
    navController.navigate(LaunchesFragmentDirections.nextFragment(model))
  }

  private fun displaySelectedScreen(itemId: Int): Boolean {
    when (itemId) {
      R.id.nav_capsules -> navController.navigate(R.id.capsules_nav_graph)
      R.id.nav_rockets -> navController.navigate(R.id.rockets_nav_graph)
      R.id.nav_ships -> navController.navigate(R.id.ships_nav_graph)
      R.id.nav_cores -> navController.navigate(R.id.cores_nav_graph)
      R.id.nav_switch_theme -> {
        switcher.isChecked = !switcher.isChecked
        return false
      }
      R.id.nav_info -> navController.navigate(R.id.infoFragment)
      R.id.nav_history -> navController.navigate(R.id.history_nav_graph)
      R.id.nav_launch_pads -> navController.navigate(R.id.launchpads_nav_graph)
      R.id.nav_social_media -> navController.navigate(R.id.socialMediaFragment)
      R.id.nav_launches -> navController.navigate(R.id.launchesFragment)
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

interface MainActivityListener{
  fun showLaunchDetails(model:LaunchModel)
}
