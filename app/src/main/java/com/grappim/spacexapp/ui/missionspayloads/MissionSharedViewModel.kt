package com.grappim.spacexapp.ui.missionspayloads

import androidx.lifecycle.*
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class MissionSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  private val _allPayloads = MutableLiveData<List<PayloadModel>>()
  val allPayloads: LiveData<List<PayloadModel>>
    get() = _allPayloads

  private val _onePayload = MutableLiveData<PayloadModel>()
  val onePayload: LiveData<PayloadModel>
    get() = _onePayload

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllPayloads() {
    fetchData(api.getAllPayloads(), _allPayloads)
  }

  // @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPayloadById(payloadId: String?) {
    fetchData(api.getPayloadById(payloadId), _onePayload)
  }
}