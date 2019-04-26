package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.*
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.fetchNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class CapsuleSharedViewModel(
  private val api: API,
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
    fetchNetworkData(api.getPastCapsules(), _pastCapsules)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCapsules() {
    Timber.d("CapsuleSharedViewModel - getUpcomingCapsules")
    fetchNetworkData(api.getUpcomingCapsules(), _upcomingCapsules)
  }

//  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//  fun getAllCapsules() {
//    Timber.d("CapsuleSharedViewModel - getAllCapsules")
//    fetchNetworkData(api.getAllCapsules(), _allCapsules)
//  }

  fun launchAllCaps(){
    viewModelScope.launch {
      getAllCaps()
    }
  }

  private suspend fun getAllCaps() = withContext(Dispatchers.IO){
    val response = api.getAllCapsules().await()
    withContext(Dispatchers.Main){
      _allCapsules.value = response
    }
  }

}