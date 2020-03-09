package com.grappim.spacexapp.ui.social_media.reddit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.RedditRepository
import javax.inject.Inject

class RedditViewModelFactory @Inject constructor(
  private val repository: RedditRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    RedditViewModel(repository) as T
}