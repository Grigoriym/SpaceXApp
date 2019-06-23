package com.grappim.spacexapp.util

import android.content.Context

class PrefsManager(
  val context: Context
) {

  private val pref = context.getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
  private val editor = pref.edit()

  fun isNightThemeEnabled(): Boolean =
    pref.getBoolean(NIGHT_THEME_PREF_KEY, false)

  fun setNightTheme(value: Boolean) {
    editor.putBoolean(NIGHT_THEME_PREF_KEY, value)
    editor.apply()
  }
}