package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.*
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class CoresSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  val allCores = MutableLiveData<List<CoreModel>>()
  val upcomingCores = MutableLiveData<List<CoreModel>>()
  val pastCores = MutableLiveData<List<CoreModel>>()

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllCapsules() {
    fetchData(api.getAllCores(), allCores)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPastCores() {
    fetchData(api.getPastCores(), pastCores)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCores() {
    fetchData(api.getUpcomingCores(), upcomingCores)
  }
}