package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import com.grappim.spacexapp.pagination.TwitterDataSourceFactory

class TwitterViewModel(
  api: TwitterApi
) : ViewModel(), LifecycleObserver {

  private val sourceFactory: TwitterDataSourceFactory = TwitterDataSourceFactory(api)
  var newTimelines: LiveData<PagedList<UserTimelineModel>>

  init {
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setEnablePlaceholders(false)
      .setPrefetchDistance(10)
      .build()
    newTimelines = LivePagedListBuilder<Long, UserTimelineModel>(sourceFactory, config).build()
  }

}