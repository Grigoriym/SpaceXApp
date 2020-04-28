package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.grappim.spacexapp.repository.TwitterPaginationRepository
import timber.log.Timber
import javax.inject.Inject

class TwitterViewModel @Inject constructor(
  private val repository: TwitterPaginationRepository
) : ViewModel(), LifecycleObserver {

  private val _currentScreenName = MutableLiveData<String>()
  val currentScreenName: LiveData<String>
    get() = _currentScreenName

  private val repoResult = map(_currentScreenName) {
    repository.getTweets(it)
  }
  val tweets = switchMap(repoResult) { it.pagedList }
  val networkState = switchMap(repoResult) { it.networkState }

  val failure = switchMap(repoResult) { it.failure }

  fun refresh() {
    Timber.d("TwitterViewModel - refresh")
    repoResult.value?.refresh?.invoke()
  }

  fun setCurrentScreenName(name: String) {
    _currentScreenName.value = name
  }

  fun showTweets() {
    repository.getTweets(_currentScreenName.value ?: "")
  }
}