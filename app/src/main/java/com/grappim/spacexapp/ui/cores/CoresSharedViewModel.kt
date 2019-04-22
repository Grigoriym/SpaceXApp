package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.*
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData
import com.grappim.spacexapp.util.fetchNetworkData
import retrofit2.Response

class CoresSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allCores = MutableLiveData<Response<List<CoreModel>>>()
  val allCores: LiveData<Response<List<CoreModel>>>
    get() = _allCores

  private val _upcomingCores = MutableLiveData<Response<List<CoreModel>>>()
  val upcomingCores: LiveData<Response<List<CoreModel>>>
    get() = _upcomingCores

  private val _pastCores = MutableLiveData<Response<List<CoreModel>>>()
  val pastCores: LiveData<Response<List<CoreModel>>>
    get() = _pastCores

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllCapsules() {
    fetchNetworkData(api.getAllCores(), _allCores)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPastCores() {
    fetchNetworkData(api.getPastCores(), _pastCores)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCores() {
    fetchNetworkData(api.getUpcomingCores(), _upcomingCores)
  }
}