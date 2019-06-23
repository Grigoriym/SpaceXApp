package com.grappim.spacexapp.pagination.twitter

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.pagination.twitter.TwitterDataSource
import timber.log.Timber

class TwitterDataSourceFactory(
  private val screenName: String? = null
) : DataSource.Factory<Long, UserTimelineModel>() {

  val sourceLiveData = MutableLiveData<TwitterDataSource>()

  override fun create(): DataSource<Long, UserTimelineModel> {
    Timber.d("TwitterDataSourceFactory - create")
    val source = TwitterDataSource(screenName)
    sourceLiveData.postValue(source)
    return source
  }
}