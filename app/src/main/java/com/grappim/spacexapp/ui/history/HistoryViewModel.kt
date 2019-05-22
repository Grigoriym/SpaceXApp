package com.grappim.spacexapp.ui.history

import androidx.lifecycle.*
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HistoryViewModel(
  private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _allHistory = MutableLiveData<Response<List<HistoryModel>>>()
  val allHistory: LiveData<Response<List<HistoryModel>>>
    get() = _allHistory

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllHistory() {
    viewModelScope.launch {
      _allHistory.value = repository.getAllHistoryFromApi().value
    }
  }
}