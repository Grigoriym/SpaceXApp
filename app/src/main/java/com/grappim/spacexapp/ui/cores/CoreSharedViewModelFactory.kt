package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class CoreSharedViewModelFactory(
  private val api: API
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    CoresSharedViewModel(api) as T
}