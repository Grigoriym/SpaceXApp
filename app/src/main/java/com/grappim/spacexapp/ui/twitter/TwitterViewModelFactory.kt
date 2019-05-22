package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.TwitterRepository

class TwitterViewModelFactory(
  private val repository: TwitterRepository
) : ViewModelProvider.NewInstanceFactory(){

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
      TwitterViewModel(repository) as T
}