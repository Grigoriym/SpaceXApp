package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import com.grappim.spacexapp.pagination.Listing
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.pagination.TwitterDataSourceFactory
import timber.log.Timber

class TwitterViewModel(
  api: TwitterApi
) : ViewModel(), LifecycleObserver {

  private val sourceFactory: TwitterDataSourceFactory = TwitterDataSourceFactory(api)
  var timelines: LiveData<PagedList<UserTimelineModel>>

  init {
    Timber.d("TwitterViewModel - init")
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setEnablePlaceholders(false)
      .setPrefetchDistance(10)
      .build()
    timelines = LivePagedListBuilder<Long, UserTimelineModel>(sourceFactory, config).build()

    val listing = Listing(
      pagedList = timelines,
      networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.networkState
      },
      retry = {},
      refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
      refreshState = MutableLiveData<NetworkState>()
    )
  }
}