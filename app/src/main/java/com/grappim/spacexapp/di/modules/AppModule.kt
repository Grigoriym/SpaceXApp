package com.grappim.spacexapp.di.modules

import com.grappim.spacexapp.core.repository.*
import com.grappim.spacexapp.ui.social_media.twitter.data.TwitterPaginationRepositoryImpl
import com.grappim.spacexapp.ui.social_media.twitter.domain.TwitterPaginationRepository
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