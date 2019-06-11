package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetAllCores
import com.grappim.spacexapp.network.gets.GetPastCores
import com.grappim.spacexapp.network.gets.GetUpcomingCores

class CoreViewModelFactory(
  private val getAllCores: GetAllCores,
  private val getPastCores: GetPastCores,
  private val getUpcomingCores: GetUpcomingCores
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    CoresViewModel(getAllCores, getPastCores, getUpcomingCores) as T
}