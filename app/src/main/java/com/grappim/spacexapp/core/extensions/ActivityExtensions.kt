package com.grappim.spacexapp.core.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.di.components.AppComponent

fun Activity.getAppComponent(): AppComponent =
    (this.application as SpaceXApplication).appComponent

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}