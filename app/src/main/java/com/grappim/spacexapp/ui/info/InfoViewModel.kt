package com.grappim.spacexapp.ui.info

import androidx.lifecycle.*
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData
import timber.log.Timber

class InfoViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _info = MutableLiveData<InfoModel>()
  val info: LiveData<InfoModel>
    get() = _info

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getInfo() {
    Timber.d("InfoViewModel - getInfo()")
    fetchData(api.getInfo(), _info)
  }
}