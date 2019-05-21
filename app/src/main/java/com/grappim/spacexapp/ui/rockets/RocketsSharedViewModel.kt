package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.*
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.fetchNetworkData
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class RocketsSharedViewModel(
  private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _allRockets = MutableLiveData<Response<List<RocketModel>>>()
  val allRockets: LiveData<Response<List<RocketModel>>>
    get() = _allRockets

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllRockets() {
    Timber.d("RocketsSharedViewModel - getAllRockets")
    viewModelScope.launch {
      _allRockets.value = repository.getAllRocketsFromApi().value
    }
  }

}