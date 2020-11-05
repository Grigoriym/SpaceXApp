package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.network.gets.GetPastCores
import com.grappim.spacexapp.network.gets.GetUpcomingCores
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase
import com.grappim.spacexapp.util.onFailure
import com.grappim.spacexapp.util.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoresViewModel @Inject constructor(
    private val getCoresUseCase: GetCoresUseCase,
    private val getPastCores: GetPastCores,
    private val getUpcomingCores: GetUpcomingCores
) : BaseViewModel() {

    private val _allCores = MutableLiveData<Resource<List<CoreModel>>>()
    val allCores: LiveData<Resource<List<CoreModel>>>
        get() = _allCores

    private val _pastCores = MutableLiveData<Resource<List<CoreModel>>>()
    val pastCores: LiveData<Resource<List<CoreModel>>>
        get() = _pastCores

    private val _upcomingCores = MutableLiveData<Resource<List<CoreModel>>>()
    val upcomingCores: LiveData<Resource<List<CoreModel>>>
        get() = _upcomingCores

    fun loadAllCores() {
        viewModelScope.launch {
            _allCores.value = Resource.Loading
            getCoresUseCase.invoke()
                .onFailure {
                    _allCores.value = Resource.Error(it)
                }.onSuccess {
                    _allCores.value = Resource.Success(it)
                }
        }
    }

    fun loadPastCores() {
        viewModelScope.launch {
            _pastCores.value = Resource.Loading
            getPastCores.invoke()
                .onFailure {
                    _pastCores.value = Resource.Error(it)
                }.onSuccess {
                    _pastCores.value = Resource.Success(it)
                }
        }
    }

    fun loadUpcomingCores() {
        viewModelScope.launch {
            _upcomingCores.value = Resource.Loading
            getUpcomingCores.invoke()
                .onFailure {
                    _upcomingCores.value = Resource.Error(it)
                }.onSuccess {
                    _upcomingCores.value = Resource.Success(it)
                }
        }
    }


}