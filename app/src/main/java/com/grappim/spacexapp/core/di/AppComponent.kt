package com.grappim.spacexapp.core.di

import android.content.Context
import com.grappim.spacexapp.pagination.reddit.RedditDataSource
import com.grappim.spacexapp.pagination.twitter.TwitterDataSource
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.ui.SplashActivity
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
}