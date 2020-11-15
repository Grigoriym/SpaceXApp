package com.grappim.spacexapp.ui.launches.completed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import com.grappim.spacexapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompletedLaunchesViewModel @Inject constructor(
    private val getPastLaunchesUseCase: GetPastLaunchesUseCase
) : BaseViewModel() {

    private val _pastLaunches = MutableLiveData<Resource<List<LaunchModel>>>()
    val pastLaunches: LiveData<Resource<List<LaunchModel>>>
        get() = _pastLaunches

    init {
        loadPastLaunches()
    }

    fun loadPastLaunches() {
        _pastLaunches.value = Resource.Loading
        viewModelScope.launch {
            getPastLaunchesUseCase.invoke()
                .onFailure {
                    _pastLaunches.value = Resource.Error(it)
                }.onSuccess {
                    _pastLaunches.value = Resource.Success(it)
                }
        }
    }


}