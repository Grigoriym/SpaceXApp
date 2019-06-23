package com.grappim.spacexapp.ui.social_media.reddit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.grappim.spacexapp.repository.RedditRepository

class RedditViewModel(
  private val repository: RedditRepository
) : ViewModel() {

  private val _currentSubreddit = MutableLiveData<String>()
  val currentSubreddit: LiveData<String>
    get() = _currentSubreddit

  private val repoResult = map(_currentSubreddit) {
    repository.getPostsBySubreddit(it)
  }
  val posts = switchMap(repoResult) { it.pagedList }
  val networkState = switchMap(repoResult) { it.networkState }
  val failure = switchMap(repoResult) { it.failure }

  fun refresh() {
    repoResult.value?.refresh?.invoke()
  }

  fun setCurrentSubreddit(subreddit: String) {
    _currentSubreddit.value = subreddit
  }

  fun showPosts() {
    repository.getPostsBySubreddit(_currentSubreddit.value ?: "")
  }

}