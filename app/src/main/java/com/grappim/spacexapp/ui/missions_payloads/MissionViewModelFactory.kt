package com.grappim.spacexapp.ui.missions_payloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetAllPayloads
import com.grappim.spacexapp.network.gets.GetPayloadById

class MissionViewModelFactory(
  private val getAllPayloads: GetAllPayloads,
  private val getPayloadById: GetPayloadById
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    MissionViewModel(getAllPayloads, getPayloadById) as T
}