package com.grappim.spacexapp.pagination.reddit

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.grappim.spacexapp.model.reddit.RedditModel
import com.grappim.spacexapp.network.NetworkHandler
import com.grappim.spacexapp.network.NetworkHelper
import com.grappim.spacexapp.network.services.RedditService
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.util.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RedditDataSource(
  private val subreddit: String? = null
) : ItemKeyedDataSource<String, RedditModel>(), KoinComponent, NetworkHelper {

  private val service: RedditService by inject()
  private val networkHandler: NetworkHandler by inject()

  val networkState = MutableLiveData<NetworkState>()
  val failure = MutableLiveData<Failure>()

  override fun loadInitial(
    params: LoadInitialParams<String>,
    callback: LoadInitialCallback<RedditModel>
  ) {
    when (networkHandler.isConnected) {
      true -> {
        networkState.postValue(NetworkState.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
          val response = service.getPostsBySubreddit(
            subreddit = subreddit,
            limit = params.requestedLoadSize
          )
          try {
            when (response.isSuccessful) {
              true -> {
                val items = response.body()?.data?.children
                  ?.map { it?.data } ?: emptyList()
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

  override fun loadAfter(
    params: LoadParams<String>,
    callback: LoadCallback<RedditModel>
  ) {
    when (networkHandler.isConnected) {
      true -> {
        networkState.postValue(NetworkState.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
          val response = service.getPostsBySubreddit(
            subreddit = subreddit,
            after = params.key,
            limit = params.requestedLoadSize
          )
          try {
            when (response.isSuccessful) {
              true -> {
                val items = response.body()?.data?.children
                  ?.map { it?.data } ?: emptyList()
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

  override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditModel>) {
    when (networkHandler.isConnected) {
      true -> {
        networkState.postValue(NetworkState.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
          val response = service.getPostsBySubreddit(
            subreddit = subreddit,
            before = params.key,
            limit = params.requestedLoadSize
          )
          try {
            when (response.isSuccessful) {
              true -> {
                val items = response.body()?.data?.children
                  ?.map { it?.data } ?: emptyList()
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

  override fun getKey(item: RedditModel): String = item.name ?: ""
}