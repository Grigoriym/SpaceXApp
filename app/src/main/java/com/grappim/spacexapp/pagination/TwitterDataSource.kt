package com.grappim.spacexapp.pagination

import androidx.lifecycle.MutableLiveData
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

  val networkState = MutableLiveData<NetworkState>()

  override fun loadInitial(
    params: LoadInitialParams<Long>,
    callback: LoadInitialCallback<UserTimelineModel>
  ) {
    Timber.d("TwitterDataSource - loadInitial")
    networkState.postValue(NetworkState.LOADING)
    CoroutineScope(Dispatchers.IO).launch {
      val response = api.testPagination().await()
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadInitial - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
        networkState.postValue(NetworkState.LOADED)
      } else {
        networkState.postValue(NetworkState.error(response.errorBody()?.string() ?: "unknown error"))
      }
    }
  }

  override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadAfter")
    networkState.postValue(NetworkState.LOADING)
    CoroutineScope(Dispatchers.IO).launch {
      val response = api.testPagination(
        maxId = params.key - 1
      ).await()
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadAfter - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
        networkState.postValue(NetworkState.LOADED)
      } else {
        networkState.postValue((NetworkState.error(response.errorBody()?.string() ?: "unknown error")))
      }
    }
  }

  override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadBefore")
    CoroutineScope(Dispatchers.IO).launch {
      val response = api.testPagination(
        sinceId = params.key
      ).await()
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadBefore - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
      }
    }
  }

  override fun getKey(item: UserTimelineModel): Long = item.id ?: 0

}