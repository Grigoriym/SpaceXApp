package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.rocket.RocketModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface SpaceXRepository {

  suspend fun getAllCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>>
  suspend fun getPastCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>>
  suspend fun getUpcomingCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>>

  suspend fun getAllRocketsFromApi(): LiveData<Response<List<RocketModel>>>

  suspend fun getAllCoresFromApi() : LiveData<Response<List<CoreModel>>>
  suspend fun getPastCoresFromApi() : LiveData<Response<List<CoreModel>>>
  suspend fun getUpcomingCoresFromApi() : LiveData<Response<List<CoreModel>>>

}