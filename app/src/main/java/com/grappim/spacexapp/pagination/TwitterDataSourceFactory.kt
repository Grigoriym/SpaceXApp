package com.grappim.spacexapp.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import timber.log.Timber

class TwitterDataSourceFactory(
  private val api: TwitterApi,
  private val screenName: String? = null
) : DataSource.Factory<Long, UserTimelineModel>() {

  val sourceLiveData = MutableLiveData<TwitterDataSource>()

  override fun create(): DataSource<Long, UserTimelineModel> {
    Timber.d("TwitterDataSourceFactory - create")
    val source = TwitterDataSource(api, screenName)
    sourceLiveData.postValue(source)
    return source
  }
}