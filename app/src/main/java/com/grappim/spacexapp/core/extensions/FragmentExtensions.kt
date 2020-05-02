package com.grappim.spacexapp.core.extensions

import androidx.fragment.app.Fragment
import com.grappim.spacexapp.core.di.AppComponent

fun Fragment.getAppComponent(): AppComponent = this.requireActivity().getAppComponent()