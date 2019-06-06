package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.network.gets.GetAllCores
import com.grappim.spacexapp.network.gets.GetPastCores
import com.grappim.spacexapp.network.gets.GetUpcomingCores
import com.grappim.spacexapp.ui.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class CoresViewModel(
  private val getAllCores: GetAllCores,
  private val getPastCores: GetPastCores,
  private val getUpcomingCores: GetUpcomingCores
) : BaseViewModel(), LifecycleObserver {

  private val _allCores = MutableLiveData<List<CoreModel>>()
  val allCores: LiveData<List<CoreModel>>
    get() = _allCores

  private fun handleAllCores(cores: List<CoreModel>) {
    this._allCores.value = cores
  }

  fun loadAllCores() = getAllCores(UseCase.None()) {
    it.either(::handleFailure, ::handleAllCores)
  }

  private val _pastCores = MutableLiveData<List<CoreModel>>()
  val pastCores: LiveData<List<CoreModel>>
    get() = _pastCores

  private fun handlePastCores(cores: List<CoreModel>) {
    this._pastCores.value = cores
  }

  fun loadPastCores() =
    getAllCores(UseCase.None()) {
      it.either(::handleFailure, ::handlePastCores)
    }

  private val _upcomingCores = MutableLiveData<List<CoreModel>>()
  val upcomingCores: LiveData<List<CoreModel>>
    get() = _upcomingCores

  private fun handleupcomingCores(cores: List<CoreModel>) {
    this._upcomingCores.value = cores
  }

  fun loadUpcomingCores() = getAllCores(UseCase.None()) {
    it.either(::handleFailure, ::handleupcomingCores)
  }

}