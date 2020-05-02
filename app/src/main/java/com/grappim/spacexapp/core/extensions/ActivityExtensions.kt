package com.grappim.spacexapp.core.extensions

import android.app.Activity
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.core.di.AppComponent

fun Activity.getAppComponent(): AppComponent =
  (this.application as SpaceXApplication).appComponent