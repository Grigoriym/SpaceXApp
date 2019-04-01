package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class ShipsSharedViewModelFactory(
  private val api: API
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    ShipsSharedViewModel(api) as T
}