package com.grappim.spacexapp.core.extensions

import android.app.Activity
import android.content.Context
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.di.components.AppComponent
import com.grappim.spacexapp.di.components.CapsulesComponent

fun Context.getCapsulesComponent(): CapsulesComponent =
    (this.applicationContext as SpaceXApplication)
        .appComponent
        .capsulesComponent()
        .create()

fun Activity.getAppComponent(): AppComponent =
    (this.application as SpaceXApplication).appComponent