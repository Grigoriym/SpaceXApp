package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.api.model.rocket.RocketModel
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import com.grappim.spacexapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketsViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase
) : BaseViewModel() {

    private val _allRockets = MutableLiveData<Resource<List<RocketModel>>>()
    val allRockets: LiveData<Resource<List<RocketModel>>>
        get() = _allRockets

    init {
        loadRockets()
    }

    fun loadRockets() {
        _allRockets.value = Resource.Loading
        viewModelScope.launch {
            getRocketsUseCase.invoke()
                .onFailure {
                    _allRockets.value = Resource.Error(it)
                }.onSuccess {
                    _allRockets.value = Resource.Success(it)
                }
        }
    }

}