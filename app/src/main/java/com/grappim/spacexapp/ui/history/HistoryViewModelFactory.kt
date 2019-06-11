package com.grappim.spacexapp.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetHistory

class HistoryViewModelFactory(
  private val getHistory: GetHistory
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    HistoryViewModel(getHistory) as T

}