package com.grappim.spacexapp.pagination

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import timber.log.Timber
import java.util.concurrent.Executors

class TwitterPaginationRepository(
  private val api: TwitterApi
) {

  fun timelines(): LiveData<PagedList<UserTimelineModel>> {
    Timber.d("TwitterPaginationRepository - timelines")
    val sourceFactory = TwitterDataSourceFactory(api)
    val livePagedList = sourceFactory.toLiveData(
      config = Config(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSizeHint = 60,
        prefetchDistance = 10
      )
    )
    Timber.d("${livePagedList.value?.get(0)?.id}")

    return livePagedList
  }

  fun timelines02() : LiveData<PagedList<UserTimelineModel>>{
    Timber.d("TwitterPaginationRepository - timelines02")
    val sourceFactory = TwitterDataSourceFactory(api)
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setEnablePlaceholders(false)
      .setPrefetchDistance(10)
      .build()

    val livePageListBuilder = LivePagedListBuilder<Long, UserTimelineModel>(
      sourceFactory,
      config
    )

    val testlplb = sourceFactory.toLiveData(
      config = Config(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSizeHint = 60,
        prefetchDistance = 10
      )
    )
    return testlplb
//    return livePageListBuilder.setFetchExecutor(Executors.newFixedThreadPool(5)).build()
  }
}