package com.grappim.spacexapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.onFailure
import com.grappim.spacexapp.util.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : BaseViewModel() {

    private val _allHistory = MutableLiveData<Resource<List<HistoryModel>>>()
    val allHistory: LiveData<Resource<List<HistoryModel>>>
        get() = _allHistory

    fun loadHistory() {
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