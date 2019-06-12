package com.grappim.spacexapp.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.NetworkHandler
import com.grappim.spacexapp.network.services.TwitterService
import com.grappim.spacexapp.util.Failure
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
  private val networkHandler: NetworkHandler by inject()

  val networkState = MutableLiveData<NetworkState>()
  val initialLoadState = MutableLiveData<NetworkState>()
  val failure = MutableLiveData<Failure>()

  override fun loadInitial(
    params: LoadInitialParams<Long>,
    callback: LoadInitialCallback<UserTimelineModel>
  ) {
    Timber.d("TwitterDataSource - loadInitial")

    when (networkHandler.isConnected) {
      true -> {
        networkState.postValue(NetworkState.LOADING)
        initialLoadState.postValue(NetworkState.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
          val response = service.getUserTimelineAsync(screenName = screenName)
          try {
            when (response.isSuccessful) {
              true -> {
                Timber.d("TwitterDataSource - loadInitial - response.isSuccessful")
                val items = response.body() ?: emptyList()
                callback.onResult(items)
                networkState.postValue(NetworkState.LOADED)
                initialLoadState.postValue(NetworkState.LOADED)
              }
              false -> {
                failure.postValue(Failure.ServerError)
              }
            }
          } catch (exception: Throwable) {
            failure.postValue(Failure.ServerError)
          }
        }
      }
      false, null -> {
        failure.postValue(Failure.NetworkConnection)
      }
    }

  }

  override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadAfter")

    when (networkHandler.isConnected) {
      true -> {
        networkState.postValue(NetworkState.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
          val response = service.getUserTimelineAsync(
            screenName = screenName,
            maxId = params.key - 1
          )
          try {
            when (response.isSuccessful) {
              true -> {
                Timber.d("TwitterDataSource - loadAfter - response.isSuccessful")
                val items = response.body() ?: emptyList()
                callback.onResult(items)
                networkState.postValue(NetworkState.LOADED)
              }
              false -> {
                failure.postValue(Failure.ServerError)
              }
            }
          } catch (exception: Throwable) {
            failure.postValue(Failure.ServerError)
          }
        }
      }
      false, null -> {
        failure.postValue(Failure.NetworkConnection)
      }
    }
  }

  override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserTimelineModel>) {
    Timber.d("TwitterDataSource - loadBefore")

    when (networkHandler.isConnected) {
      true -> {
        networkState.postValue(NetworkState.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
          try {
            val response = service.getUserTimelineAsync(
              screenName = screenName,
              sinceId = params.key
            )
            when (response.isSuccessful) {
              true -> {
                Timber.d("TwitterDataSource - loadBefore - response.isSuccessful")
                val items = response.body() ?: emptyList()
                callback.onResult(items)
                networkState.postValue(NetworkState.LOADED)
              }
              false -> {
                failure.postValue(Failure.ServerError)
              }
            }
          } catch (exception: Throwable) {
            failure.postValue(Failure.ServerError)
          }
        }
      }
      false, null -> {
        failure.postValue(Failure.NetworkConnection)
      }
    }
  }

  override fun getKey(item: UserTimelineModel): Long = item.id ?: 0

}