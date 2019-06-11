package com.grappim.spacexapp.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.services.TwitterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class TwitterDataSource(
  private val screenName: String? = null
) : ItemKeyedDataSource<Long, UserTimelineModel>(), KoinComponent {

  private val service: TwitterService by inject()

  val networkState = MutableLiveData<NetworkState>()
  val initialLoadState = MutableLiveData<NetworkState>()

  override fun loadInitial(
    params: LoadInitialParams<Long>,
    callback: LoadInitialCallback<UserTimelineModel>
  ) {
    Timber.d("TwitterDataSource - loadInitial")
    networkState.postValue(NetworkState.LOADING)
    initialLoadState.postValue(NetworkState.LOADING)
    CoroutineScope(Dispatchers.IO).launch {
      val response = service.getUserTimelineAsync(screenName = screenName)
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadInitial - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
        networkState.postValue(NetworkState.LOADED)
        initialLoadState.postValue(NetworkState.LOADED)
      } else {
        networkState.postValue(
          NetworkState.error(
            response.errorBody()?.string() ?: "unknown error"
          )
        )
        initialLoadState.postValue(
          NetworkState.error(
            response.errorBody()?.string() ?: "unknown error"
          )
        )
      }
    }
  }

  override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadAfter")
    networkState.postValue(NetworkState.LOADING)
    CoroutineScope(Dispatchers.IO).launch {
      val response = service.getUserTimelineAsync(
        screenName = screenName,
        maxId = params.key - 1
      )
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadAfter - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
        networkState.postValue(NetworkState.LOADED)
      } else {
        networkState.postValue(
          (NetworkState.error(
            response.errorBody()?.string() ?: "unknown error"
          ))
        )
      }
    }
  }

  override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadBefore")
    networkState.postValue(NetworkState.LOADING)
    CoroutineScope(Dispatchers.IO).launch {
      val response = service.getUserTimelineAsync(
        screenName = screenName,
        sinceId = params.key
      )
      if (response.isSuccessful) {
        Timber.d("TwitterDataSource - loadBefore - response.isSuccessful")
        val items = response.body() ?: emptyList()
        callback.onResult(items)
        networkState.postValue(NetworkState.LOADED)
      } else {
        networkState.postValue(
          (NetworkState.error(
            response.errorBody()?.string() ?: "unknown error"
          ))
        )
      }
    }
  }

  override fun getKey(item: UserTimelineModel): Long = item.id ?: 0

}