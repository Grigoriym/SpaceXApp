package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
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

  fun refresh() {//todo I think it doesn't work
    Timber.d("TwitterViewModel - refresh")
    repoResult.value?.refresh?.invoke()
  }

  fun showTweets(value: String) {
    Timber.d("TwitterViewModel - showTweets - $value")
    screenName.value = value
  }

}