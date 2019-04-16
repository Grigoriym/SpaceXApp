package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.*
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class ShipsSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allShips = MutableLiveData<List<ShipModel>>()
  val allShips: LiveData<List<ShipModel>>
    get() = _allShips

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllShips() {
    fetchData(api.getAllShips(), _allShips)
  }
}