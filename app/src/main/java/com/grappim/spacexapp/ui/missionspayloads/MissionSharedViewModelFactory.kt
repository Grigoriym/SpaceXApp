package com.grappim.spacexapp.ui.missionspayloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class MissionSharedViewModelFactory(
  private val api: API
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    MissionSharedViewModel(api) as T
}