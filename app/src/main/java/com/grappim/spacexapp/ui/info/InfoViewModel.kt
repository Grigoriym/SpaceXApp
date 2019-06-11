package com.grappim.spacexapp.ui.info

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.network.gets.GetInfo
import com.grappim.spacexapp.ui.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class InfoViewModel(
  private val getInfo: GetInfo
) : BaseViewModel(), LifecycleObserver {

  private val _info = MutableLiveData<InfoModel>()
  val info: LiveData<InfoModel>
    get() = _info

  private fun handleInfo(info: InfoModel) {
    this._info.value = info
  }

  fun loadInfo() =
    getInfo(UseCase.None()) {
      it.either(::handleFailure, ::handleInfo)
    }

  override fun onCleared() {
    super.onCleared()
    getInfo.unBind()
  }

}