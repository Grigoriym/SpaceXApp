package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.*
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ShipsSharedViewModel(
  private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _allShips = MutableLiveData<Response<List<ShipModel>>>()
  val allShips: LiveData<Response<List<ShipModel>>>
    get() = _allShips

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllShips() {
    viewModelScope.launch {
      _allShips.value = repository.getAllShipsFromApi().value
    }
  }
}