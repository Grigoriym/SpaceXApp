package com.grappim.spacexapp.core.extensions

import android.app.Activity
import android.content.Context
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.di.components.AppComponent
import com.grappim.spacexapp.di.components.CapsuleComponent
import com.grappim.spacexapp.di.components.CoresComponent
import com.grappim.spacexapp.di.components.FragmentsComponent

fun Context.getFragmentsComponent(): FragmentsComponent =
    (this.applicationContext as SpaceXApplication)
        .appComponent
        .fragmentsComponent()
        .create()

fun Context.getCapsuleComponent(): CapsuleComponent =
    (this.applicationContext as SpaceXApplication)
        .appComponent
        .capsulesComponent()
        .create()

fun Context.getCoresComponent(): CoresComponent =
    (this.applicationContext as SpaceXApplication)
        .appComponent
        .coresComponent()
        .create()

fun Activity.getAppComponent(): AppComponent =
    (this.application as SpaceXApplication).appComponent