package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.gets.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val getModule = Kodein.Module("gets") {
  bind() from singleton { GetRockets(instance()) }
  bind() from singleton { GetAllCapsules(instance()) }
  bind() from singleton { GetPastCapsules(instance()) }
  bind() from singleton { GetUpcomingCapsules(instance()) }
  bind() from singleton { GetAllCores(instance()) }
  bind() from singleton { GetPastCores(instance()) }
  bind() from singleton { GetUpcomingCores(instance()) }
  bind() from singleton { GetAllShips(instance()) }
  bind() from singleton { GetAllLaunchPads(instance()) }
  bind() from singleton { GetInfo(instance()) }
  bind() from singleton { GetHistory(instance()) }
  bind() from singleton { GetAllPayloads(instance()) }
  bind() from singleton { GetPayloadById(instance()) }
}