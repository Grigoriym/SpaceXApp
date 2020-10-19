package com.grappim.spacexapp.core.extensions

import androidx.fragment.app.Fragment
import com.grappim.spacexapp.di.components.AppComponent

fun Fragment.getAppComponent(): AppComponent = this.requireActivity().getAppComponent()