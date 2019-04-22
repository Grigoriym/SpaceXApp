package com.grappim.spacexapp.ui.info

import androidx.lifecycle.*
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchNetworkData
import retrofit2.Response
import timber.log.Timber

class InfoViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _info = MutableLiveData<Response<InfoModel>>()
  val info: LiveData<Response<InfoModel>>
    get() = _info

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getInfo() {
    Timber.d("InfoViewModel - getInfo()")
    fetchNetworkData(api.getInfo(), _info)
  }
}