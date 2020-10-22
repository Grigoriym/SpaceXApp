package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.gets.GetPastCapsules
import com.grappim.spacexapp.network.gets.GetUpcomingCapsules
import com.grappim.spacexapp.ui.base.BaseViewModel
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class CapsulesViewModel @Inject constructor(
  private val getCapsulesUseCase: GetCapsulesUseCase,
  private val getUpcomingCapsules: GetUpcomingCapsules,
  private val getPastCapsules: GetPastCapsules
) : BaseViewModel() {

    private val _allCapsules = MutableLiveData<List<CapsuleModel>>()
    val allCapsules: LiveData<List<CapsuleModel>>
        get() = _allCapsules

    private val _upcomingCapsules = MutableLiveData<List<CapsuleModel>>()
    val upcomingCapsules: LiveData<List<CapsuleModel>>
        get() = _upcomingCapsules

    private val _pastCapsules = MutableLiveData<List<CapsuleModel>>()
    val pastCapsules: LiveData<List<CapsuleModel>>
        get() = _pastCapsules

    fun loadAllCapsules() =
        getCapsulesUseCase(UseCase.None()) {
            it.either(::handleFailure, ::handleCapsules)
        }

    fun loadUpcomingCapsules() =
        getUpcomingCapsules(UseCase.None()) {
            it.either(::handleFailure, ::handleUpcomingCapsules)
        }

    fun loadPastCapsules() =
        getPastCapsules(UseCase.None()) {
            it.either(::handleFailure, ::handlePastCapsules)
        }

    private fun handleUpcomingCapsules(capsules: List<CapsuleModel>) {
        this._upcomingCapsules.value = capsules
    }

    private fun handlePastCapsules(capsules: List<CapsuleModel>) {
        this._pastCapsules.value = capsules
    }

    private fun handleCapsules(capsules: List<CapsuleModel>) {
        this._allCapsules.value = capsules
    }

    override fun onCleared() {
        getCapsulesUseCase.unBind()
        getPastCapsules.unBind()
        getUpcomingCapsules.unBind()
        super.onCleared()
    }
}