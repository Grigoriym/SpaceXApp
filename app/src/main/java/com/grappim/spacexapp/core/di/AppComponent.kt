package com.grappim.spacexapp.core.di

import android.content.Context
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.pagination.reddit.RedditDataSource
import com.grappim.spacexapp.pagination.twitter.TwitterDataSource
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.ui.SplashActivity
import com.grappim.spacexapp.ui.capsules.GetCapsulesFragment
import com.grappim.spacexapp.ui.cores.GetCoresFragment
import com.grappim.spacexapp.ui.history.HistoryFragment
import com.grappim.spacexapp.ui.info.InfoFragment
import com.grappim.spacexapp.ui.launches.completed.CompletedLaunchesFragment
import com.grappim.spacexapp.ui.launches.upcoming.UpcomingLaunchesFragment
import com.grappim.spacexapp.ui.launchpads.GetLaunchPadsFragment
import com.grappim.spacexapp.ui.missions_payloads.MissionFragment
import com.grappim.spacexapp.ui.rockets.GetRocketsFragment
import com.grappim.spacexapp.ui.ships.GetShipsFragment
import com.grappim.spacexapp.ui.social_media.reddit.RedditFragment
import com.grappim.spacexapp.ui.social_media.twitter.TwitterFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(mainActivity: MainActivity)
  fun inject(splashActivity: SplashActivity)

  fun inject(redditDataSource: RedditDataSource)
  fun inject(redditFragment: RedditFragment)

  fun inject(twitterDataSource: TwitterDataSource)
  fun inject(twitterFragment: TwitterFragment)

  fun inject(spaceXRepository: SpaceXRepository)

  fun inject(getCoresFragment: GetCoresFragment)
  fun inject(historyFragment: HistoryFragment)
  fun inject(getCapsulesFragment: GetCapsulesFragment)
  fun inject(infoFragment: InfoFragment)
  fun inject(completedLaunchesFragment: CompletedLaunchesFragment)
  fun inject(upcomingLaunchesFragment: UpcomingLaunchesFragment)
  fun inject(getLaunchPadsFragment: GetLaunchPadsFragment)
  fun inject(missionFragment: MissionFragment)
  fun inject(getRocketsFragment: GetRocketsFragment)
  fun inject(getShipsFragment: GetShipsFragment)
}