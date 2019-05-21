package com.grappim.spacexapp.ui.info

import androidx.lifecycle.*
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class InfoViewModel(
  private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _info = MutableLiveData<Response<InfoModel>>()
  val info: LiveData<Response<InfoModel>>
    get() = _info

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getInfo() {
    Timber.d("InfoViewModel - getInfo()")
    viewModelScope.launch {
      _info.value = repository.getInfoFromApi().value
    }
  }
}