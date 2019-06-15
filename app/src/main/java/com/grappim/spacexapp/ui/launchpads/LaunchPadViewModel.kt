package com.grappim.spacexapp.ui.launchpads

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.network.gets.GetAllLaunchPads
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class LaunchPadViewModel(
  private val getAllLaunchPads: GetAllLaunchPads
) : BaseViewModel(), LifecycleObserver {

  private val _allLaunchPads = MutableLiveData<List<LaunchPadModel>>()
  val allLaunchPads: LiveData<List<LaunchPadModel>>
    get() = _allLaunchPads

  private fun handleAllLaunchPads(launchPads: List<LaunchPadModel>) {
    this._allLaunchPads.value = launchPads
  }

  fun loadAllLaunchPads() =
    getAllLaunchPads(UseCase.None()) {
      it.either(::handleFailure, ::handleAllLaunchPads)
    }

  override fun onCleared() {
    super.onCleared()
    getAllLaunchPads.unBind()
  }

}