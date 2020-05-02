package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetAllCapsules
import com.grappim.spacexapp.network.gets.GetPastCapsules
import com.grappim.spacexapp.network.gets.GetUpcomingCapsules
import javax.inject.Inject

class CapsuleViewModelFactory @Inject constructor(
  private val getAllCapsules: GetAllCapsules,
  private val getUpcomingCapsules: GetUpcomingCapsules,
  private val getPastCapsules: GetPastCapsules
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    CapsulesViewModel(getAllCapsules, getUpcomingCapsules, getPastCapsules) as T

}