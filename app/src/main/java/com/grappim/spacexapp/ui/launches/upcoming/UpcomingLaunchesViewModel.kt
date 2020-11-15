package com.grappim.spacexapp.ui.launches.upcoming

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

class UpcomingLaunchesViewModel @Inject constructor(
    private val getUpcomingLaunchesUseCase: GetUpcomingLaunchesUseCase
) : BaseViewModel() {

    private val _upcomingLaunches = MutableLiveData<Resource<List<LaunchModel>>>()
    val upcomingLaunches: LiveData<Resource<List<LaunchModel>>>
        get() = _upcomingLaunches

    init {
        loadAllLaunches()
    }

    fun loadAllLaunches() {
        _upcomingLaunches.value = Resource.Loading
        viewModelScope.launch {
            getUpcomingLaunchesUseCase.invoke()
                .onFailure {
                    _upcomingLaunches.value = Resource.Error(it)
                }.onSuccess {
                    _upcomingLaunches.value = Resource.Success(it)
                }
        }
    }

}