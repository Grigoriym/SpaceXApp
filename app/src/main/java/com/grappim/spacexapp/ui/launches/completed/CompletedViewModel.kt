package com.grappim.spacexapp.ui.launches.completed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.network.gets.GetPastLaunches
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class CompletedViewModel(
  private val getPastLaunches: GetPastLaunches
) : BaseViewModel() {

  private val _pastLaunches = MutableLiveData<List<LaunchModel>>()
  val pastLaunches: LiveData<List<LaunchModel>>
    get() = _pastLaunches

  private fun handleLaunches(launches: List<LaunchModel>) {
    this._pastLaunches.value = launches
  }

  fun loadPastLaunches() =
    getPastLaunches(UseCase.None()) {
      it.either(::handleFailure, ::handleLaunches)
    }

  override fun onCleared() {
    super.onCleared()
    getPastLaunches.unBind()
  }
}