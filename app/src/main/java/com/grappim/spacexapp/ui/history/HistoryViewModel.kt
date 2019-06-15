package com.grappim.spacexapp.ui.history

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.network.gets.GetHistory
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class HistoryViewModel(
  private val getHistory: GetHistory
) : BaseViewModel(), LifecycleObserver {

  private val _allHistory = MutableLiveData<List<HistoryModel>>()
  val allHistory: LiveData<List<HistoryModel>>
    get() = _allHistory

  private fun handleHistory(history: List<HistoryModel>) {
    this._allHistory.value = history
  }

  fun loadHistory() =
    getHistory(UseCase.None()) {
      it.either(::handleFailure, ::handleHistory)
    }

  override fun onCleared() {
    super.onCleared()
    getHistory.unBind()
  }
}