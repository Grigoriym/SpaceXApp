package com.grappim.spacexapp.ui.history

import androidx.lifecycle.*
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class HistoryViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allHistory = MutableLiveData<List<HistoryModel>>()
  val allHistory: LiveData<List<HistoryModel>>
    get() = _allHistory

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllHistory() {
    fetchData(api.getHistory(), _allHistory)
  }
}