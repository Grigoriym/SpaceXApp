package com.grappim.spacexapp.core.di

import com.grappim.spacexapp.core.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

  @Binds
  abstract fun provideRedditRepository(redditRepositoryImpl: RedditRepositoryImpl): RedditRepository

  @Binds
  abstract fun provideTwitterRepository(
    twitterRepositoryImpl: TwitterPaginationRepositoryImpl
  ): TwitterPaginationRepository

  @Binds
  abstract fun provideSpaceXrepository(
    spaceXRepositoryImpl: SpaceXRepositoryImpl
  ): SpaceXRepository

}