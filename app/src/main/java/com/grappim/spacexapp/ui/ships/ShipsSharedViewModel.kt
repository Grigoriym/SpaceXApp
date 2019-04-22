package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.*
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchNetworkData
import retrofit2.Response

class ShipsSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allShips = MutableLiveData<Response<List<ShipModel>>>()
  val allShips: LiveData<Response<List<ShipModel>>>
    get() = _allShips

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllShips() {
    fetchNetworkData(api.getAllShips(), _allShips)
  }
}