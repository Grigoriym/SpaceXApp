package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.network.gets.GetAllCores
import com.grappim.spacexapp.network.gets.GetPastCores
import com.grappim.spacexapp.network.gets.GetUpcomingCores
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class CoresViewModel @Inject constructor(
  private val getAllCores: GetAllCores,
  private val getPastCores: GetPastCores,
  private val getUpcomingCores: GetUpcomingCores
) : BaseViewModel(), LifecycleObserver {

  private val _allCores = MutableLiveData<List<CoreModel>>()
  val allCores: LiveData<List<CoreModel>>
    get() = _allCores

  private val _pastCores = MutableLiveData<List<CoreModel>>()
  val pastCores: LiveData<List<CoreModel>>
    get() = _pastCores

  private val _upcomingCores = MutableLiveData<List<CoreModel>>()
  val upcomingCores: LiveData<List<CoreModel>>
    get() = _upcomingCores

  fun loadAllCores() =
    getAllCores(UseCase.None()) {
      it.either(::handleFailure, ::handleAllCores)
    }

  fun loadPastCores() =
    getPastCores(UseCase.None()) {
      it.either(::handleFailure, ::handlePastCores)
    }

  fun loadUpcomingCores() =
    getUpcomingCores(UseCase.None()) {
      it.either(::handleFailure, ::handleupcomingCores)
    }

  private fun handlePastCores(cores: List<CoreModel>) {
    this._pastCores.value = cores
  }

  private fun handleupcomingCores(cores: List<CoreModel>) {
    this._upcomingCores.value = cores
  }

  private fun handleAllCores(cores: List<CoreModel>) {
    this._allCores.value = cores
  }

  override fun onCleared() {
    super.onCleared()
    getAllCores.unBind()
    getPastCores.unBind()
    getUpcomingCores.unBind()
  }

}