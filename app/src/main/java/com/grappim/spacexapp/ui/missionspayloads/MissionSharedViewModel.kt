package com.grappim.spacexapp.ui.missionspayloads

import androidx.lifecycle.*
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.fetchNetworkData
import kotlinx.coroutines.launch
import retrofit2.Response

class MissionSharedViewModel(
  private val repository: SpaceXRepository
) : ViewModel(), LifecycleObserver {

  private val _allPayloads = MutableLiveData<Response<List<PayloadModel>>>()
  val allPayloads: LiveData<Response<List<PayloadModel>>>
    get() = _allPayloads

  private val _onePayload = MutableLiveData<Response<PayloadModel>>()
  val onePayload: LiveData<Response<PayloadModel>>
    get() = _onePayload

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllPayloads() {
    viewModelScope.launch {
      _allPayloads.value = repository.getAllPayloadsFromApi().value
    }
  }

   @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPayloadById(payloadId: String?) {
    viewModelScope.launch {
      _onePayload.value = repository.getPayloadByIdFromApi(payloadId).value
    }
  }
}