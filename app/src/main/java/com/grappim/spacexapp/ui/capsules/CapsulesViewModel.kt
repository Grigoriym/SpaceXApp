package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.api.model.capsule.CapsuleModel
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class CapsulesViewModel @Inject constructor(
    private val getCapsulesUseCase: GetCapsulesUseCase,
    private val getUpcomingCapsulesUseCase: GetUpcomingCapsulesUseCase,
    private val getPastCapsulesUseCase: GetPastCapsulesUseCase
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
        _allCapsules.value = Resource.Loading
        viewModelScope.launch {
            getCapsulesUseCase.invoke()
                .onFailure {
                    _allCapsules.value = Resource.Error(it)
                }.onSuccess {
                    _allCapsules.value = Resource.Success(it)
                }
        }
    }

    fun loadUpcomingCapsules() {
        _upcomingCapsules.value = Resource.Loading
        viewModelScope.launch {
            getUpcomingCapsulesUseCase.invoke()
                .onFailure {
                    _upcomingCapsules.value = Resource.Error(it)
                }.onSuccess {
                    _upcomingCapsules.value = Resource.Success(it)
                }
        }
    }

    fun loadPastCapsules() {
        _pastCapsules.value = Resource.Loading
        viewModelScope.launch {
            getPastCapsulesUseCase.invoke()
                .onFailure {
                    _pastCapsules.value = Resource.Error(it)
                }.onSuccess {
                    _pastCapsules.value = Resource.Success(it)
                }
        }
    }

}