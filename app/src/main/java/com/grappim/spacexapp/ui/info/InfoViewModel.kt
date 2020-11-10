package com.grappim.spacexapp.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.api.model.info.InfoModel
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoViewModel @Inject constructor(
    private val getInfoUseCase: GetInfoUseCase
) : BaseViewModel() {

    private val _info = MutableLiveData<Resource<InfoModel>>()
    val info: LiveData<Resource<InfoModel>>
        get() = _info

    fun loadInfo() {
        _info.value = Resource.Loading
        viewModelScope.launch {
            getInfoUseCase.invoke()
                .onFailure {
                    _info.value = Resource.Error(it)
                }.onSuccess {
                    _info.value = Resource.Success(it)
                }
        }
    }

}