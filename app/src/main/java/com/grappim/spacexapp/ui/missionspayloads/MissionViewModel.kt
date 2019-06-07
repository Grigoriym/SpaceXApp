package com.grappim.spacexapp.ui.missionspayloads

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.network.gets.GetAllPayloads
import com.grappim.spacexapp.network.gets.GetPayloadById
import com.grappim.spacexapp.ui.BaseViewModel
import com.grappim.spacexapp.util.UseCase

class MissionViewModel(
  private val getAllPayloads: GetAllPayloads,
  private val getPayloadById: GetPayloadById
) : BaseViewModel(), LifecycleObserver {

  private val _allPayloads = MutableLiveData<List<PayloadModel>>()
  val allPayloads: LiveData<List<PayloadModel>>
    get() = _allPayloads

  private val _onePayload = MutableLiveData<PayloadModel>()
  val onePayload: LiveData<PayloadModel>
    get() = _onePayload

  private fun handleAllPayloads(payloads: List<PayloadModel>) {
    this._allPayloads.value = payloads
  }

  private fun handleOnePayload(payload: PayloadModel) {
    this._onePayload.value = payload
  }

  fun loadAllPayloads() =
    getAllPayloads(UseCase.None()) {
      it.either(::handleFailure, ::handleAllPayloads)
    }

  fun loadPayloadById(payloadId: String?) =
    getPayloadById(GetPayloadById.Params(payloadId)) {
      it.either(::handleFailure, ::handleOnePayload)
    }

}