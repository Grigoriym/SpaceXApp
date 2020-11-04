package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.gets.GetPastCapsules
import com.grappim.spacexapp.network.gets.GetUpcomingCapsules
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.onFailure
import com.grappim.spacexapp.util.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class CapsulesViewModel @Inject constructor(
    private val getCapsulesUseCase: GetCapsulesUseCase,
    private val getUpcomingCapsules: GetUpcomingCapsules,
    private val getPastCapsules: GetPastCapsules
) : BaseViewModel() {

    private val _allCapsules = MutableLiveData<Resource<List<CapsuleModel>>>()
    val allCapsules: LiveData<Resource<List<CapsuleModel>>>
        get() = _allCapsules

    private val _upcomingCapsules = MutableLiveData<Resource<List<CapsuleModel>>>()
    val upcomingCapsules: LiveData<Resource<List<CapsuleModel>>>
        get() = _upcomingCapsules

    private val _pastCapsules = MutableLiveData<Resource<List<CapsuleModel>>>()
    val pastCapsules: LiveData<Resource<List<CapsuleModel>>>
        get() = _pastCapsules

    fun loadAllCapsules() {
        viewModelScope.launch {
            _allCapsules.value = Resource.Loading
            getCapsulesUseCase.invoke()
                .onFailure {
                    _allCapsules.value = Resource.Error(it)
                }.onSuccess {
                    _allCapsules.value = Resource.Success(it)
                }
        }
    }

    fun loadUpcomingCapsules() {
        viewModelScope.launch {
            _upcomingCapsules.value = Resource.Loading
            getUpcomingCapsules.invoke()
                .onFailure {
                    _upcomingCapsules.value = Resource.Error(it)
                }.onSuccess {
                    _upcomingCapsules.value = Resource.Success(it)
                }
        }
    }

    fun loadPastCapsules() {
        viewModelScope.launch {
            _pastCapsules.value = Resource.Loading
            getPastCapsules.invoke()
                .onFailure {
                    _pastCapsules.value = Resource.Error(it)
                }.onSuccess {
                    _pastCapsules.value = Resource.Success(it)
                }
        }
    }

}