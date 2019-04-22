package com.grappim.spacexapp.ui.launchpads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class LaunchPadViewModelFactory(
  private val api:API
) : ViewModelProvider.NewInstanceFactory(){
  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
      LaunchPadViewModel(api) as T
}