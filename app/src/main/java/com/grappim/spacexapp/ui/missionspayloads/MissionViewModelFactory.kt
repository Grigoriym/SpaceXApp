package com.grappim.spacexapp.ui.missionspayloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.SpaceXRepository

class MissionViewModelFactory(
  private val repository: SpaceXRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    MissionViewModel(repository) as T
}