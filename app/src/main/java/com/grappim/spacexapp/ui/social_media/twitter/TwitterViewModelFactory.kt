package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.core.repository.TwitterPaginationRepository
import javax.inject.Inject

class TwitterViewModelFactory @Inject constructor(
  private val repository: TwitterPaginationRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    TwitterViewModel(repository) as T
}