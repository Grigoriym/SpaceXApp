package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.*
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class CoresSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allCores = MutableLiveData<List<CoreModel>>()
  val allCores: LiveData<List<CoreModel>>
    get() = _allCores

  private val _upcomingCores = MutableLiveData<List<CoreModel>>()
  val upcomingCores: LiveData<List<CoreModel>>
    get() = _upcomingCores

  private val _pastCores = MutableLiveData<List<CoreModel>>()
  val pastCores: LiveData<List<CoreModel>>
    get() = _pastCores

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllCapsules() {
    fetchData(api.getAllCores(), _allCores)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPastCores() {
    fetchData(api.getPastCores(), _pastCores)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCores() {
    fetchData(api.getUpcomingCores(), _upcomingCores)
  }
}