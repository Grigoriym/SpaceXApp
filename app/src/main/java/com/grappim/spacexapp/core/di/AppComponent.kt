package com.grappim.spacexapp.core.di

import android.content.Context
import com.grappim.spacexapp.ui.social_media.reddit.RedditFragment
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(redditFragment: RedditFragment)
}