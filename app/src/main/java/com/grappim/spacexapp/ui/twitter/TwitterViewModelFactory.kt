package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.TwitterApi

class TwitterViewModelFactory(
  private val api: TwitterApi
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    TwitterViewModel(api) as T
}