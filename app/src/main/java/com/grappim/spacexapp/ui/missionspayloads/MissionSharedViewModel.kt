package com.grappim.spacexapp.ui.missionspayloads

import androidx.lifecycle.*
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class MissionSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  val allPayloads = MutableLiveData<List<PayloadModel>>()

  val onePayload = MutableLiveData<PayloadModel>()

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllPayloads() {
    fetchData(api.getAllPayloads(), allPayloads)
  }

  // @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPayloadById(payloadId: String?) {
    fetchData(api.getPayloadById(payloadId), onePayload)
  }
}