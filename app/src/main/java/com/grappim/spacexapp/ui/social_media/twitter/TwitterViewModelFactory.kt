package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.TwitterPaginationRepository

class TwitterViewModelFactory(
  private val repository: TwitterPaginationRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    TwitterViewModel(repository) as T
}