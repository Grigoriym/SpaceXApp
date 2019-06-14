package com.grappim.spacexapp.ui.launches.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.network.gets.GetUpcomingLaunches
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class UpcomingViewModel(
  private val getUpcomingLaunches: GetUpcomingLaunches
) : BaseViewModel() {

  private val _upcomingLaunches = MutableLiveData<List<LaunchModel>>()
  val upcomingLaunches: LiveData<List<LaunchModel>>
    get() = _upcomingLaunches

  private fun handleLaunches(launches: List<LaunchModel>) {
    this._upcomingLaunches.value = launches
  }

  fun loadAllLaunches() =
    getUpcomingLaunches(UseCase.None()) {
      it.either(::handleFailure, ::handleLaunches)
    }

  override fun onCleared() {
    super.onCleared()
    getUpcomingLaunches.unBind()
  }
}