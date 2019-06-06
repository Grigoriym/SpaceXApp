package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetAllShips

class ShipsViewModelFactory(
  private val getAllShips: GetAllShips
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    ShipsViewModel(getAllShips) as T
}