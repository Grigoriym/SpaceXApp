package com.grappim.spacexapp.di.components

import com.grappim.spacexapp.di.scopes.FragmentScope
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
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentsComponent
    }

    fun inject(rocketsFragment: GetRocketsFragment)

    fun inject(historyFragment: HistoryFragment)

    fun inject(getLaunchPadsFragment: GetLaunchPadsFragment)

    fun inject(completedLaunchesFragment: CompletedLaunchesFragment)

    fun inject(upcomingLaunchesFragment: UpcomingLaunchesFragment)

    fun inject(missionFragment: MissionFragment)

    fun inject(getShipsFragment: GetShipsFragment)

    fun inject(infoFragment: InfoFragment)

    fun inject(twitterFragment: TwitterFragment)

    fun inject(redditFragment: RedditFragment)

}