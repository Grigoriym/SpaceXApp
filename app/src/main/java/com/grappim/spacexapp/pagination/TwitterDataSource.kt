package com.grappim.spacexapp.pagination

import androidx.paging.ItemKeyedDataSource
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class TwitterDataSource(
  private val api: TwitterApi
) : ItemKeyedDataSource<Long, UserTimelineModel>() {

  override fun loadInitial(
    params: LoadInitialParams<Long>,
    callback: LoadInitialCallback<UserTimelineModel>
  ) {
    Timber.d("TwitterDataSource - loadInitial")
    CoroutineScope(Dispatchers.IO).launch {
      val response = api.testPagination().await()
//      withContext(Dispatchers.Main){
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadInitial - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
      }
//      }
    }
  }

  override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadAfter")
    CoroutineScope(Dispatchers.IO).launch {
      val response = api.testPagination(
        sinceId = params.key
      ).await()
//      withContext(Dispatchers.Main){
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadAfter - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
      }
//      }
    }
  }

  override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
  }

  override fun getKey(item: UserTimelineModel): Long = item.id ?: 0

}