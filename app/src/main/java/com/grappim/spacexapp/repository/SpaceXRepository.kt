package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface SpaceXRepository {

  suspend fun getAllCapsulesFromApi():LiveData<Response<List<CapsuleModel>>>
  suspend fun getPastCapsulesFromApi():LiveData<Response<List<CapsuleModel>>>
  suspend fun getUpcomingCapsulesFromApi():LiveData<Response<List<CapsuleModel>>>

}