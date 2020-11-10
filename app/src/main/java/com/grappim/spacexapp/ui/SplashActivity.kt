package com.grappim.spacexapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.grappim.spacexapp.core.extensions.getAppComponent
import com.grappim.spacexapp.core.extensions.launchActivity
import com.grappim.spacexapp.core.utils.PrefsManager
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        Timber.d("SplashActivity - onCreate")
        initDayNightMode()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        this.launchActivity<MainActivity> { }
        finish()
    }

    private fun initDayNightMode() {
        if (!prefs.isNightThemeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
