package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchNetworkData
import retrofit2.Response

class RocketsSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allRockets = MutableLiveData<Response<List<RocketModel>>>()
  val allRockets: LiveData<Response<List<RocketModel>>>
    get() = _allRockets

  fun getAllRockets() {
    fetchNetworkData(api.getAllRockets(), _allRockets)
  }

}