package com.grappim.spacexapp.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetInfo

class InfoViewModelFactory(
  private val getInfo: GetInfo
) : ViewModelProvider.NewInstanceFactory() {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    InfoViewModel(getInfo) as T
}