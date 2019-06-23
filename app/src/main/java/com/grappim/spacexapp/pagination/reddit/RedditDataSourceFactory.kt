package com.grappim.spacexapp.pagination.reddit

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.grappim.spacexapp.model.reddit.RedditModel

class RedditDataSourceFactory(
  private val subreddit: String? = null
) : DataSource.Factory<String, RedditModel>() {

  val sourceLiveData = MutableLiveData<RedditDataSource>()

  override fun create(): DataSource<String, RedditModel> {
    val source = RedditDataSource(subreddit)
    sourceLiveData.postValue(source)
    return source
  }
}