package com.grappim.spacexapp.core.repository

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.pagination.Listing
import com.grappim.spacexapp.pagination.twitter.TwitterDataSourceFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TwitterPaginationRepositoryImpl @Inject constructor() : TwitterPaginationRepository {

  override fun getTweets(screenName: String): Listing<UserTimelineModel> {
    Timber.d("TwitterPaginationRepositoryImpl - getTweets - $screenName")
    val sourceFactory =
      TwitterDataSourceFactory(screenName)
    val livePagedList = sourceFactory.toLiveData(
      config = Config(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSizeHint = 60,
        prefetchDistance = 20
      )
    )

    return Listing(
      pagedList = livePagedList,
      networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.networkState
      },
      refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
      failure = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.failure
      }
    )
  }
}