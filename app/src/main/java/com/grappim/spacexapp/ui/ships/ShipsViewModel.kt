package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.gets.GetAllShips
import com.grappim.spacexapp.ui.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class ShipsViewModel(
  private val getAllShips: GetAllShips
) : BaseViewModel(), LifecycleObserver {

  private val _allShips = MutableLiveData<List<ShipModel>>()
  val allShips: LiveData<List<ShipModel>>
    get() = _allShips

  private fun handleAllShips(ships: List<ShipModel>) {
    this._allShips.value = ships
  }

  fun loadAllShips() =
    getAllShips(UseCase.None()) {
      it.either(::handleFailure, ::handleAllShips)
    }

  override fun onCleared() {
    super.onCleared()
    getAllShips.unBind()
  }

}