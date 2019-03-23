package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class CapsuleSharedViewModelFactory(
  private val api: API
) : ViewModelProvider.NewInstanceFactory(){

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return CapsuleSharedViewModel(api) as T
  }
}