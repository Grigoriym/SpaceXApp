package com.grappim.spacexapp.core.di

import com.grappim.spacexapp.repository.RedditRepository
import com.grappim.spacexapp.repository.RedditRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

  @Binds
  abstract fun provideRedditRepository(redditRepositoryImpl: RedditRepositoryImpl): RedditRepository

}