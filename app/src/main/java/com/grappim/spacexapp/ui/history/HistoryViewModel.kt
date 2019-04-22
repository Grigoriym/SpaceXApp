package com.grappim.spacexapp.ui.history

import androidx.lifecycle.*
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData
import com.grappim.spacexapp.util.fetchNetworkData
import retrofit2.Response

class HistoryViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allHistory = MutableLiveData<Response<List<HistoryModel>>>()
  val allHistory: LiveData<Response<List<HistoryModel>>>
    get() = _allHistory

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllHistory() {
    fetchNetworkData(api.getHistory(), _allHistory)
  }
}