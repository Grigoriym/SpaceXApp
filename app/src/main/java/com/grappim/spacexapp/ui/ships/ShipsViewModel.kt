package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.api.model.ships.ShipModel
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.core.functional.onFailure
import com.grappim.spacexapp.core.functional.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShipsViewModel @Inject constructor(
    private val getAllShipsUseCase: GetAllShipsUseCase
) : BaseViewModel() {

    private val _allShips = MutableLiveData<Resource<List<ShipModel>>>()
    val allShips: LiveData<Resource<List<ShipModel>>>
        get() = _allShips

    fun loadAllShips() {
        viewModelScope.launch {
            getAllShipsUseCase.invoke()
                .onFailure {
                    _allShips.value = Resource.Error(it)
                }.onSuccess {
                    _allShips.value = Resource.Success(it)
                }
        }
    }


}