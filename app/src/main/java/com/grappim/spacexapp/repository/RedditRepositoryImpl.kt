package com.grappim.spacexapp.repository

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.grappim.spacexapp.model.reddit.RedditModel
import com.grappim.spacexapp.pagination.Listing
import com.grappim.spacexapp.pagination.reddit.RedditDataSourceFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditRepositoryImpl @Inject constructor() : RedditRepository {
  override fun getPostsBySubreddit(subReddit: String): Listing<RedditModel> {
    val sourceFactory = RedditDataSourceFactory(subReddit)
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
      networkState = Transformations.switchMap(
        sourceFactory.sourceLiveData
      ) {
        it.networkState
      },
      refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
      failure = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.failure
      }
    )
  }
}