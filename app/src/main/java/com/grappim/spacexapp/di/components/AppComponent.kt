package com.grappim.spacexapp.di.components

import android.content.Context
import com.grappim.spacexapp.di.modules.AppModule
import com.grappim.spacexapp.di.modules.GsonModule
import com.grappim.spacexapp.di.modules.NetworkModule
import com.grappim.spacexapp.di.modules.ViewModelModule
import com.grappim.spacexapp.di.scopes.AppScope
import com.grappim.spacexapp.di.scopes.GsonScope
import com.grappim.spacexapp.pagination.reddit.RedditDataSource
import com.grappim.spacexapp.pagination.twitter.TwitterDataSource
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.ui.SplashActivity
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
import dagger.android.AndroidInjectionModule

@AppScope
@GsonScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        GsonModule::class,
        AppSubComponents::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent

    }

    fun fragmentsComponent(): FragmentsComponent.Factory
    fun capsulesComponent(): CapsuleComponent.Factory
    fun coresComponent(): CoresComponent.Factory

    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)

    fun inject(redditDataSource: RedditDataSource)
    fun inject(redditFragment: RedditFragment)

    fun inject(twitterDataSource: TwitterDataSource)
    fun inject(twitterFragment: TwitterFragment)

    fun inject(infoFragment: InfoFragment)
}