package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import com.grappim.spacexapp.pagination.Listing
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.pagination.TwitterDataSourceFactory
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import timber.log.Timber

class TwitterViewModel(
  private val repository: TwitterPaginationRepository
) : ViewModel(), LifecycleObserver {

  private val screenName = MutableLiveData<String>()
  private val repoResult = map(screenName) {
    repository.getTweets(it)
  }
  val tweets = switchMap(repoResult) { it.pagedList }
  val networkState = switchMap(repoResult) { it.networkState }
  val refreshState = switchMap(repoResult) { it.refreshState }

  fun refresh() {
    Timber.d("TwitterViewModel - refresh")
    repoResult.value?.refresh?.invoke()
  }

  fun retry() {
    val listing = repoResult.value
    listing?.retry?.invoke()
  }

  fun showTweets(value:String){
    screenName.value = value
  }

  init {
//    Timber.d("TwitterViewModel - init")
//    val config = PagedList.Config.Builder()
//      .setPageSize(30)
//      .setEnablePlaceholders(false)
//      .setPrefetchDistance(10)
//      .build()
//    timelines = LivePagedListBuilder<Long, UserTimelineModel>(sourceFactory, config).build()
  }
}