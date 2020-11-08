package com.grappim.spacexapp.ui.launches.completed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase
import com.grappim.spacexapp.util.onFailure
import com.grappim.spacexapp.util.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompletedLaunchesViewModel @Inject constructor(
  private val getPastLaunchesUseCase: GetPastLaunchesUseCase
) : BaseViewModel() {

    private val _pastLaunches = MutableLiveData<Resource<List<LaunchModel>>>()
    val pastLaunches: LiveData<Resource<List<LaunchModel>>>
        get() = _pastLaunches

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