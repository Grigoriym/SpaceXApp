package com.grappim.spacexapp.core.di

import com.grappim.spacexapp.repository.RedditRepository
import com.grappim.spacexapp.repository.RedditRepositoryImpl
import com.grappim.spacexapp.repository.TwitterPaginationRepository
import com.grappim.spacexapp.repository.TwitterPaginationRepositoryImpl
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

}