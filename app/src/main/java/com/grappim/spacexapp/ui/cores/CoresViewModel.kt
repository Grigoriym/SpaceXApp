package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.api.model.cores.CoreModel
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class CoresViewModel @AssistedInject constructor(
    private val getCoresUseCase: GetCoresUseCase,
    private val getPastCoresUseCase: GetPastCoresUseCase,
    private val getUpcomingCoresUseCase: GetUpcomingCoresUseCase,
    @Assisted private val coreType: CoreArgs
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(coreType: CoreArgs): CoresViewModel
    }

    private val _allCores = MutableLiveData<Resource<List<CoreModel>>>()
    val allCores: LiveData<Resource<List<CoreModel>>>
        get() = _allCores

    private val _pastCores = MutableLiveData<Resource<List<CoreModel>>>()
    val pastCores: LiveData<Resource<List<CoreModel>>>
        get() = _pastCores

    private val _upcomingCores = MutableLiveData<Resource<List<CoreModel>>>()
    val upcomingCores: LiveData<Resource<List<CoreModel>>>
        get() = _upcomingCores

    init {
        loadCores()
    }

    fun loadCores() {
        when (coreType) {
            CoreArgs.ALL_CORES -> loadAllCores()
            CoreArgs.PAST_CORES -> loadPastCores()
            CoreArgs.UPCOMING_CORES -> loadUpcomingCores()
        }
    }

    private fun loadAllCores() {
        _allCores.value = Resource.Loading
        viewModelScope.launch {
            getCoresUseCase.invoke()
                .onFailure {
                    _allCores.value = Resource.Error(it)
                }.onSuccess {
                    _allCores.value = Resource.Success(it)
                }
        }
    }

    private fun loadPastCores() {
        _pastCores.value = Resource.Loading
        viewModelScope.launch {
            getPastCoresUseCase.invoke()
                .onFailure {
                    _pastCores.value = Resource.Error(it)
                }.onSuccess {
                    _pastCores.value = Resource.Success(it)
                }
        }
    }

    private fun loadUpcomingCores() {
        _upcomingCores.value = Resource.Loading
        viewModelScope.launch {
            getUpcomingCoresUseCase.invoke()
                .onFailure {
                    _upcomingCores.value = Resource.Error(it)
                }.onSuccess {
                    _upcomingCores.value = Resource.Success(it)
                }
        }
    }


}