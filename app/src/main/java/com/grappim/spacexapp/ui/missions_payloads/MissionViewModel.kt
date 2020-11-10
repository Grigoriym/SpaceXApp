package com.grappim.spacexapp.ui.missions_payloads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.onFailure
import com.grappim.spacexapp.util.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class MissionViewModel @Inject constructor(
    private val getAllPayloadsUseCase: GetAllPayloadsUseCase,
    private val getPayloadByIdUseCase: GetPayloadByIdUseCase
) : BaseViewModel() {

    private val _allPayloads = MutableLiveData<Resource<List<PayloadModel>>>()
    val allPayloads: LiveData<Resource<List<PayloadModel>>>
        get() = _allPayloads

    private val _onePayload = MutableLiveData<Resource<PayloadModel>>()
    val onePayload: LiveData<Resource<PayloadModel>>
        get() = _onePayload

    fun loadAllPayloads() {
        _allPayloads.value = Resource.Loading
        viewModelScope.launch {
            getAllPayloadsUseCase.invoke()
                .onFailure {
                    _allPayloads.value = Resource.Error(it)
                }.onSuccess {
                    _allPayloads.value = Resource.Success(it)
                }
        }
    }

    fun loadPayloadById(payloadId: String) {
        _onePayload.value = Resource.Loading
        viewModelScope.launch {
            getPayloadByIdUseCase.invoke(payloadId)
                .onFailure {
                    _onePayload.value = Resource.Error(it)
                }.onSuccess {
                    _onePayload.value = Resource.Success(it)
                }
        }
    }

}