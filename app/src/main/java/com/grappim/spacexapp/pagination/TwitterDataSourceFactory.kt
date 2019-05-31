package com.grappim.spacexapp.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import timber.log.Timber

class TwitterDataSourceFactory(
  private val api: TwitterApi
) : DataSource.Factory<Long, UserTimelineModel>() {

  private val sourceLiveData = MutableLiveData<TwitterDataSource>()

  override fun create(): DataSource<Long, UserTimelineModel> {
    Timber.d("TwitterDataSourceFactory - create")
    val source = TwitterDataSource(api)
    sourceLiveData.postValue(source)
    return source
  }
}