package com.grappim.spacexapp.core.utils

import android.content.Context
import javax.inject.Inject

class PrefsManager @Inject constructor(
  val context: Context
) {

  private val pref = context.getSharedPreferences(PREFS_MANAGER, Context.MODE_PRIVATE)
  private val editor = pref.edit()

  fun isNightThemeEnabled(): Boolean =
    pref.getBoolean(NIGHT_THEME_PREF_KEY, false)

  fun setNightTheme(value: Boolean) {
    editor.putBoolean(NIGHT_THEME_PREF_KEY, value).apply()
  }
}