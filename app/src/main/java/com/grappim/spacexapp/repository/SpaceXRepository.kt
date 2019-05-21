package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import retrofit2.Response

interface SpaceXRepository {

  suspend fun getAllCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>>
  suspend fun getPastCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>>
  suspend fun getUpcomingCapsulesFromApi(): LiveData<Response<List<CapsuleModel>>>

  suspend fun getAllRocketsFromApi(): LiveData<Response<List<RocketModel>>>

  suspend fun getAllCoresFromApi(): LiveData<Response<List<CoreModel>>>
  suspend fun getPastCoresFromApi(): LiveData<Response<List<CoreModel>>>
  suspend fun getUpcomingCoresFromApi(): LiveData<Response<List<CoreModel>>>

  suspend fun getAllHistoryFromApi(): LiveData<Response<List<HistoryModel>>>

  suspend fun getInfoFromApi(): LiveData<Response<InfoModel>>

  suspend fun getAllLaunchPadsFromApi(): LiveData<Response<List<LaunchPadModel>>>

  suspend fun getAllShipsFromApi(): LiveData<Response<List<ShipModel>>>

  suspend fun getAllPayloadsFromApi(): LiveData<Response<List<PayloadModel>>>
  suspend fun getPayloadByIdFromApi(payloadId: String?): LiveData<Response<PayloadModel>>

}