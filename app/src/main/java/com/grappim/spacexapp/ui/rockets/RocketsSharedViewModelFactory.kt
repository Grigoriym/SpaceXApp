package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class RocketsSharedViewModelFactory(
  private val api:API
) : ViewModelProvider.NewInstanceFactory(){

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return RocketsSharedViewModel(api) as T
  }
}