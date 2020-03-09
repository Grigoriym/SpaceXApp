package com.grappim.spacexapp.core.di

import android.content.Context
import com.grappim.spacexapp.pagination.reddit.RedditDataSource
import com.grappim.spacexapp.ui.social_media.reddit.RedditFragment
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

  fun inject(redditFragment: RedditFragment)
  fun inject(redditDataSource: RedditDataSource)
}