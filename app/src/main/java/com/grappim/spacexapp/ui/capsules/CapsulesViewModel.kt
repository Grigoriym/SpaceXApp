package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.gets.GetAllCapsules
import com.grappim.spacexapp.network.gets.GetPastCapsules
import com.grappim.spacexapp.network.gets.GetUpcomingCapsules
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class CapsulesViewModel(
  private val getAllCapsules: GetAllCapsules,
  private val getUpcomingCapsules: GetUpcomingCapsules,
  private val getPastCapsules: GetPastCapsules
) : BaseViewModel(), LifecycleObserver {

  private val _allCapsules = MutableLiveData<List<CapsuleModel>>()
  val allCapsules: LiveData<List<CapsuleModel>>
    get() = _allCapsules

  fun loadAllCapsules() =
    getAllCapsules(UseCase.None()) {
      it.either(::handleFailure, ::handleCapsules)
    }

  private fun handleCapsules(capsules: List<CapsuleModel>) {
    this._allCapsules.value = capsules
  }

  private val _upcomingCapsules = MutableLiveData<List<CapsuleModel>>()
  val upcomingCapsules: LiveData<List<CapsuleModel>>
    get() = _upcomingCapsules

  fun loadUpcomingCapsules() =
    getUpcomingCapsules(UseCase.None()) {
      it.either(::handleFailure, ::handleUpcomingCapsules)
    }

  private fun handleUpcomingCapsules(capsules: List<CapsuleModel>) {
    this._upcomingCapsules.value = capsules
  }

  private val _pastCapsules = MutableLiveData<List<CapsuleModel>>()
  val pastCapsules: LiveData<List<CapsuleModel>>
    get() = _pastCapsules

  fun loadPastCapsules() =
    getPastCapsules(UseCase.None()) {
      it.either(::handleFailure, ::handlePastCapsules)
    }

  private fun handlePastCapsules(capsules: List<CapsuleModel>) {
    this._pastCapsules.value = capsules
  }

  override fun onCleared() {
    super.onCleared()
    getAllCapsules.unBind()
    getPastCapsules.unBind()
    getUpcomingCapsules.unBind()
  }
}