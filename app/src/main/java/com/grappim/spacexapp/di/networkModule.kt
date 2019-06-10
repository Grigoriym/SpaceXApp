package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.NetworkHandler
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val networkModule = Kodein.Module("networkModule") {
  bind() from singleton { NetworkHandler(instance()) }
}