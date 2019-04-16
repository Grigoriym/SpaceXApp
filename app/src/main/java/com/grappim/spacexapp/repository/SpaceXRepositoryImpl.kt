package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SpaceXRepositoryImpl(
  val api: API
) : SpaceXRepository {
  override fun getCapsules(): LiveData<List<CapsuleModel>> {
    TODO()
  }
}