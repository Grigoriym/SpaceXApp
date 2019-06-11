package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.NetworkHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
  single { NetworkHandler(androidContext()) }
}