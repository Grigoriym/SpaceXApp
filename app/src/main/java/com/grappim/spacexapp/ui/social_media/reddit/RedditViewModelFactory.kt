package com.grappim.spacexapp.ui.social_media.reddit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.RedditRepository

class RedditViewModelFactory(
  private val repository: RedditRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    RedditViewModel(repository) as T
}