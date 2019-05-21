package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SpaceXRepositoryImpl(
    val api: API
) : SpaceXRepository {
  override suspend fun getAllCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> {
    val data: MutableLiveData<Response<List<CapsuleModel>>> = MutableLiveData()
    withContext(Dispatchers.IO) {
      val response = api.getAllCapsules().await()
      withContext(Dispatchers.Main) {
        data.value = response
      }
    }
    return data
  }

  override suspend fun getPastCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> {
    val data: MutableLiveData<Response<List<CapsuleModel>>> = MutableLiveData()
    withContext(Dispatchers.IO) {
      val response = api.getPastCapsules().await()
      withContext(Dispatchers.Main) {
        data.value = response
      }
    }
    return data
  }

  override suspend fun getUpcomingCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> {
    val data: MutableLiveData<Response<List<CapsuleModel>>> = MutableLiveData()
    withContext(Dispatchers.IO) {
      val response = api.getUpcomingCapsules().await()
      withContext(Dispatchers.Main) {
        data.value = response
      }
    }
    return data
  }
}