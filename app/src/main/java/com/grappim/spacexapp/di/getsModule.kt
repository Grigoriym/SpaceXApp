package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.gets.*
import org.koin.dsl.module

val getModule = module {
  single { GetRockets(get()) }
  single { GetAllCapsules(get()) }
  single { GetPastCapsules(get()) }
  single { GetUpcomingCapsules(get()) }
  single { GetAllCores(get()) }
  single { GetPastCores(get()) }
  single { GetUpcomingCores(get()) }
  single { GetAllShips(get()) }
  single { GetAllLaunchPads(get()) }
  single { GetInfo(get()) }
  single { GetHistory(get()) }
  single { GetAllPayloads(get()) }
  single { GetPayloadById(get()) }
}