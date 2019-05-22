package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.*
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class CapsuleSharedViewModel(
    private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _allCapsules = MutableLiveData<Response<List<CapsuleModel>>>()
  val allCapsules: LiveData<Response<List<CapsuleModel>>>
    get() = _allCapsules

  private val _upcomingCapsules = MutableLiveData<Response<List<CapsuleModel>>>()
  val upcomingCapsules: LiveData<Response<List<CapsuleModel>>>
    get() = _upcomingCapsules

  private val _pastCapsules = MutableLiveData<Response<List<CapsuleModel>>>()
  val pastCapsules: LiveData<Response<List<CapsuleModel>>>
    get() = _pastCapsules

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPastCapsules() {
    Timber.d("CapsuleSharedViewModel - getPastCapsules")
    viewModelScope.launch {
      _allCapsules.value = repository.getAllCapsulesFromApi().value
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCapsules() {
    Timber.d("CapsuleSharedViewModel - getUpcomingCapsules")
    viewModelScope.launch {
      _upcomingCapsules.value = repository.getUpcomingCapsulesFromApi().value
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllCapsules() {
    Timber.d("CapsuleSharedViewModel - getAllCapsules")
    viewModelScope.launch {
      _allCapsules.value = repository.getAllCapsulesFromApi().value
    }
  }

}