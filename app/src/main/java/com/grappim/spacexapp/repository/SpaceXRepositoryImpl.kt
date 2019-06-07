package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.api.API
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class SpaceXRepositoryImpl(
  val api: API
) : SpaceXRepository, RepositoryHelper {

  override suspend fun <T> generalRequest(liveData: Deferred<T>): LiveData<T> {
    Timber.d("SpaceXRepositoryImpl generalRequest")
    val data: MutableLiveData<T> = MutableLiveData()
    withContext(Dispatchers.IO) {
      val response = liveData.await()
      withContext(Dispatchers.Main) {
        data.value = response
      }
    }
    return data
  }

  override suspend fun getAllCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> =
    generalRequest(api.getAllCapsules())

  override suspend fun getPastCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> =
    generalRequest(api.getPastCapsules())

  override suspend fun getUpcomingCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>> =
    generalRequest(api.getUpcomingCapsules())

  override suspend fun getAllRocketsFromApi(): LiveData<Response<List<RocketModel>>> =
    generalRequest(api.getAllRockets())

  override suspend fun getAllCoresFromApi(): LiveData<Response<List<CoreModel>>> =
    generalRequest(api.getAllCores())

  override suspend fun getPastCoresFromApi(): LiveData<Response<List<CoreModel>>> =
    generalRequest(api.getPastCores())

  override suspend fun getUpcomingCoresFromApi(): LiveData<Response<List<CoreModel>>> =
    generalRequest(api.getUpcomingCores())

  override suspend fun getAllHistoryFromApi(): LiveData<Response<List<HistoryModel>>> =
    generalRequest(api.getHistory())

  override suspend fun getInfoFromApi(): LiveData<Response<InfoModel>> =
    generalRequest(api.getInfo())

  override suspend fun getAllLaunchPadsFromApi(): LiveData<Response<List<LaunchPadModel>>> =
    generalRequest(api.getAllLaunchPads())

  override suspend fun getAllShipsFromApi(): LiveData<Response<List<ShipModel>>> =
    generalRequest(api.getAllShips())

  override suspend fun getAllPayloadsFromApi(): LiveData<Response<List<PayloadModel>>> =
    generalRequest(api.getAllPayloads())

  override suspend fun getPayloadByIdFromApi(payloadId: String?): LiveData<Response<PayloadModel>> =
    generalRequest(api.getPayloadById(payloadId))
}