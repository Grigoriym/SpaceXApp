package com.grappim.spacexapp.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API

class HistoryViewModelFactory(
  private val api: API
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    HistoryViewModel(api) as T

}