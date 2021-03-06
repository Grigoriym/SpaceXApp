package com.grappim.spacexapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.api.model.history.HistoryModel
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import com.grappim.spacexapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : BaseViewModel() {

    private val _allHistory = MutableLiveData<Resource<List<HistoryModel>>>()
    val allHistory: LiveData<Resource<List<HistoryModel>>>
        get() = _allHistory

    init {
        loadHistory()
    }

    fun loadHistory() {
        _allHistory.value = Resource.Loading
        viewModelScope.launch {
            getHistoryUseCase.invoke()
                .onFailure {
                    _allHistory.value = Resource.Error(it)
                }.onSuccess {
                    _allHistory.value = Resource.Success(it)
                }
        }
    }

}