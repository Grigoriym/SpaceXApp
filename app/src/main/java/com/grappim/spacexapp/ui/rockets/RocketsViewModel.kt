package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.gets.GetRockets
import com.grappim.spacexapp.ui.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class RocketsViewModel(
  private val getRockets: GetRockets
) : BaseViewModel(), LifecycleObserver {

  private val _allRockets = MutableLiveData<List<RocketModel>>()
  val allRockets: LiveData<List<RocketModel>>
    get() = _allRockets

  fun loadRockets() =
    getRockets(UseCase.None()) {
      it.either(::handleFailure, ::handleRockets)
    }

  private fun handleRockets(rockets: List<RocketModel>) {
    this._allRockets.value = rockets
  }

}