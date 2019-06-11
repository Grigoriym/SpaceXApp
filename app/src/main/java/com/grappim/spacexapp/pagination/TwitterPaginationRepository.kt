package com.grappim.spacexapp.pagination

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import timber.log.Timber

class TwitterPaginationRepository {

  fun getTweets(screenName: String): Listing<UserTimelineModel> {
    Timber.d("TwitterPaginationRepository - getTweets - $screenName")
    val sourceFactory = TwitterDataSourceFactory(screenName)
    val livePagedList = sourceFactory.toLiveData(
      config = Config(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSizeHint = 60,
        prefetchDistance = 20
      )
    )

    val initialLoadState = Transformations.switchMap(sourceFactory.sourceLiveData) {
      it.initialLoadState
    }

    return Listing(
      pagedList = livePagedList,
      networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.networkState
      },
      refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
      initialLoadState = initialLoadState
    )
  }
}