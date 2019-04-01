package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.*
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class ShipsSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver{

  val allShips = MutableLiveData<List<ShipModel>>()

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllShips() {
    fetchData(api.getAllShips(), allShips)
  }
}