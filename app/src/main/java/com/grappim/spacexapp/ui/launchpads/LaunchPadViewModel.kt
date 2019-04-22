package com.grappim.spacexapp.ui.launchpads

import androidx.lifecycle.*
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchNetworkData
import retrofit2.Response
import timber.log.Timber

class LaunchPadViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allLaunchPads = MutableLiveData<Response<List<LaunchPadModel>>>()
  val allLaunchPads: LiveData<Response<List<LaunchPadModel>>>
    get() = _allLaunchPads

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllLaunchPads() {
    Timber.d("LaunchPadViewModel - getAllLaunchPads()")
    fetchNetworkData(api.getAllLaunchPads(), _allLaunchPads)
  }
}