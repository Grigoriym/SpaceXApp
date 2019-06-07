package com.grappim.spacexapp.di

import com.grappim.spacexapp.ui.capsules.CapsuleViewModelFactory
import com.grappim.spacexapp.ui.cores.CoreViewModelFactory
import com.grappim.spacexapp.ui.history.HistoryViewModelFactory
import com.grappim.spacexapp.ui.info.InfoViewModelFactory
import com.grappim.spacexapp.ui.launchpads.LaunchPadViewModelFactory
import com.grappim.spacexapp.ui.missionspayloads.MissionViewModelFactory
import com.grappim.spacexapp.ui.rockets.RocketsViewModelFactory
import com.grappim.spacexapp.ui.ships.ShipsViewModelFactory
import com.grappim.spacexapp.ui.social_media.twitter.TwitterViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val viewModelFactoryModule = Kodein.Module("vmf") {
  bind() from provider { CapsuleViewModelFactory(instance(), instance(), instance()) }
  bind() from provider { RocketsViewModelFactory(instance()) }
  bind() from provider { CoreViewModelFactory(instance(), instance(), instance()) }
  bind() from provider { ShipsViewModelFactory(instance()) }
  bind() from provider { MissionViewModelFactory(instance()) }
  bind() from provider { InfoViewModelFactory(instance()) }
  bind() from provider { HistoryViewModelFactory(instance()) }
  bind() from provider { LaunchPadViewModelFactory(instance()) }
  bind() from provider { TwitterViewModelFactory(instance()) }
}