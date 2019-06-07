package com.grappim.spacexapp.pagination

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.api.TwitterApi
import timber.log.Timber

class TwitterPaginationRepository(
  private val api: TwitterApi
) {

  fun getTweets(screenName: String): Listing<UserTimelineModel> {
    Timber.d("TwitterPaginationRepository - getTweets - $screenName")
    val sourceFactory = TwitterDataSourceFactory(api, screenName)
    val livePagedList = sourceFactory.toLiveData(
      config = Config(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSizeHint = 60,
        prefetchDistance = 20
      )
    )

    val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
      it.initialLoad
    }

    return Listing(
      pagedList = livePagedList,
      networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.networkState
      },
      refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
      refreshState = refreshState
    )
  }
}