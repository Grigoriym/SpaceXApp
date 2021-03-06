package com.grappim.spacexapp.ui.launchpads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.api.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import com.grappim.spacexapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchPadViewModel @Inject constructor(
    private val getAllLaunchPadsUseCase: GetAllLaunchPadsUseCase
) : BaseViewModel() {

    private val _allLaunchPads = MutableLiveData<Resource<List<LaunchPadModel>>>()
    val allLaunchPads: LiveData<Resource<List<LaunchPadModel>>>
        get() = _allLaunchPads

    init {
        loadAllLaunchPads()
    }

    fun loadAllLaunchPads() {
        _allLaunchPads.value = Resource.Loading
        viewModelScope.launch {
            getAllLaunchPadsUseCase.invoke()
                .onFailure {
                    _allLaunchPads.value = Resource.Error(it)
                }.onSuccess {
                    _allLaunchPads.value = Resource.Success(it)
                }
        }
    }

}