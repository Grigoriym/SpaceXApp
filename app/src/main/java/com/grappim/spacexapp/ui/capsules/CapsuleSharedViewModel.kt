package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.*
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.fetchData
import timber.log.Timber

class CapsuleSharedViewModel(
  private val api: API,
  private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _allCapsules = MutableLiveData<List<CapsuleModel>>()
  val allCapsules: LiveData<List<CapsuleModel>>
    get() = _allCapsules

  private val _upcomingCapsules = MutableLiveData<List<CapsuleModel>>()
  val upcomingCapsules: LiveData<List<CapsuleModel>>
    get() = _upcomingCapsules

  private val _pastCapsules = MutableLiveData<List<CapsuleModel>>()
  val pastCapsules: LiveData<List<CapsuleModel>>
    get() = _pastCapsules

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPastCapsules() {
    Timber.d("CapsuleSharedViewModel - getPastCapsules")
    fetchData(api.getPastCapsules(), _pastCapsules)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCapsules() {
    Timber.d("CapsuleSharedViewModel - getUpcomingCapsules")
    fetchData(api.getUpcomingCapsules(), _upcomingCapsules)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllCapsules() {
    Timber.d("CapsuleSharedViewModel - getAllCapsules")
    fetchData(api.getCapsules(), _allCapsules)
    // _allCapsules.value = repository.getCapsules().value
  }

}