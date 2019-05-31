package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.TwitterApi
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.repository.TwitterRepository

class TwitterViewModelFactory(
  private val repository: TwitterRepository,
  private val testRepo: TwitterPaginationRepository,
  private val api: TwitterApi
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    TwitterViewModel(repository, testRepo, api) as T
}