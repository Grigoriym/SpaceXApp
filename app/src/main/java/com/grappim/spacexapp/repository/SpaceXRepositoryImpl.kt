package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.API
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SpaceXRepositoryImpl(
  val api: API
) : SpaceXRepository {

  private suspend fun <T> generalRequest(liveData: Deferred<T>): LiveData<T> {
    val data: MutableLiveData<T> = MutableLiveData()
    withContext(Dispatchers.IO) {
      val response = liveData.await()
      withContext(Dispatchers.Main) {
        data.value = response
      }
    }
    return data
  }

  override suspend fun getAllCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> {
    return generalRequest(api.getAllCapsules())
  }

  override suspend fun getPastCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> {
    return generalRequest(api.getPastCapsules())
  }

  override suspend fun getUpcomingCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> {
    return generalRequest(api.getUpcomingCapsules())
  }

  override suspend fun getAllRocketsFromApi(): LiveData<Response<List<RocketModel>>> {
    return generalRequest(api.getAllRockets())
  }

}