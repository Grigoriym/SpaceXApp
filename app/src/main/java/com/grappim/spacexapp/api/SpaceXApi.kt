package com.grappim.spacexapp.api

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.mission.MissionModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceXApi {

  @GET("launches")
  suspend fun getAllLaunches(): Response<List<LaunchModel>>

  @GET("launches/past")
  suspend fun getPastLaunches(
    @Query("order") order: String? = "desc"
  ): Response<List<LaunchModel>>

  @GET("launches/upcoming")
  suspend fun getUpcomingLaunches(): Response<List<LaunchModel>>

  @GET("launches/next")
  suspend fun getNextLaunch(): Response<LaunchModel>

  @GET("launches/latest")
  suspend fun getLatestLaunch(): Response<LaunchModel>

  @GET("launches/{flightNumber}")
  suspend fun getOneLaunch(
    @Path("flightNumber") flightNumber: Int?
  ): Response<LaunchModel>

  @GET("rockets")
  suspend fun getAllRockets(): Response<List<RocketModel>>

  @GET("capsules")
  suspend fun getAllCapsules(): List<CapsuleModel>

  @GET("capsules/upcoming")
  suspend fun getUpcomingCapsules(): List<CapsuleModel>

  @GET("capsules/past")
  suspend fun getPastCapsules(): List<CapsuleModel>

  @GET("capsules/{capsuleSerial}")
  suspend fun getOneCapsuleBySerial(
    @Path("capsuleSerial") capsuleSerial: String?
  ): Response<CapsuleModel>

  @GET("cores")
  suspend fun getAllCores(): Response<List<CoreModel>>

  @GET("cores/upcoming")
  suspend fun getUpcomingCores(): Response<List<CoreModel>>

  @GET("cores/past")
  suspend fun getPastCores(): Response<List<CoreModel>>

  @GET("cores/{coreSerial}")
  suspend fun getOneCoreBySerial(
    @Path("coreSerial") coreSerial: String?
  ): Response<CoreModel>

  @GET("ships")
  suspend fun getAllShips(): Response<List<ShipModel>>

  @GET("ships/{shipId}")
  suspend fun getOneShipById(
    @Path("shipId") shipId: String?
  ): Response<ShipModel>

  @GET("launchpads")
  suspend fun getAllLaunchPads(): Response<List<LaunchPadModel>>

  @GET("launchpads/{site_id}")
  suspend fun getLaunchPadBySiteId(
    @Path("site_id") siteId: String?
  ): Response<LaunchPadModel>

  @GET("info")
  suspend fun getInfo(): Response<InfoModel>

  @GET("history")
  suspend fun getHistory(): Response<List<HistoryModel>>

  @GET("payloads")
  suspend fun getAllPayloads(): Response<List<PayloadModel>>

  @GET("payloads/{payloadId}")
  suspend fun getPayloadById(
    @Path("payloadId") payloadId: String?
  ): Response<PayloadModel>

  @GET("missions")
  suspend fun getAllMissions(): Response<List<MissionModel>>

  @GET("missions/{missionId}")
  suspend fun getOneMissionById(
    @Path("missionId") missionId: String?
  ): Response<MissionModel>
}