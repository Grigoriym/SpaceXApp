package com.grappim.spacexapp.api

import com.grappim.spacexapp.api.model.capsule.CapsuleModel
import com.grappim.spacexapp.api.model.cores.CoreModel
import com.grappim.spacexapp.api.model.history.HistoryModel
import com.grappim.spacexapp.api.model.info.InfoModel
import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.api.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.api.model.mission.MissionModel
import com.grappim.spacexapp.api.model.payloads.PayloadModel
import com.grappim.spacexapp.api.model.rocket.RocketModel
import com.grappim.spacexapp.api.model.ships.ShipModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceXApi {

    @GET("launches")
    suspend fun getAllLaunches(): List<LaunchModel>

    @GET("launches/past")
    suspend fun getPastLaunches(
        @Query("order") order: String? = "desc"
    ): List<LaunchModel>

    @GET("launches/upcoming")
    suspend fun getUpcomingLaunches(): List<LaunchModel>

    @GET("launches/next")
    suspend fun getNextLaunch(): LaunchModel

    @GET("launches/latest")
    suspend fun getLatestLaunch(): LaunchModel

    @GET("launches/{flightNumber}")
    suspend fun getOneLaunch(
        @Path("flightNumber") flightNumber: Int?
    ): LaunchModel

    @GET("rockets")
    suspend fun getAllRockets(): List<RocketModel>

    @GET("capsules")
    suspend fun getAllCapsules(): List<CapsuleModel>

    @GET("capsules/upcoming")
    suspend fun getUpcomingCapsules(): List<CapsuleModel>

    @GET("capsules/past")
    suspend fun getPastCapsules(): List<CapsuleModel>

    @GET("capsules/{capsuleSerial}")
    suspend fun getOneCapsuleBySerial(
        @Path("capsuleSerial") capsuleSerial: String?
    ): CapsuleModel

    @GET("cores")
    suspend fun getAllCores(): List<CoreModel>

    @GET("cores/upcoming")
    suspend fun getUpcomingCores(): List<CoreModel>

    @GET("cores/past")
    suspend fun getPastCores(): List<CoreModel>

    @GET("cores/{coreSerial}")
    suspend fun getOneCoreBySerial(
        @Path("coreSerial") coreSerial: String?
    ): CoreModel

    @GET("ships")
    suspend fun getAllShips(): List<ShipModel>

    @GET("ships/{shipId}")
    suspend fun getOneShipById(
        @Path("shipId") shipId: String?
    ): Response<ShipModel>

    @GET("launchpads")
    suspend fun getAllLaunchPads(): List<LaunchPadModel>

    @GET("launchpads/{site_id}")
    suspend fun getLaunchPadBySiteId(
        @Path("site_id") siteId: String?
    ): LaunchPadModel

    @GET("info")
    suspend fun getInfo(): InfoModel

    @GET("history")
    suspend fun getHistory(): List<HistoryModel>

    @GET("payloads")
    suspend fun getAllPayloads(): List<PayloadModel>

    @GET("payloads/{payloadId}")
    suspend fun getPayloadById(
        @Path("payloadId") payloadId: String?
    ): PayloadModel

    @GET("missions")
    suspend fun getAllMissions(): List<MissionModel>

    @GET("missions/{missionId}")
    suspend fun getOneMissionById(
        @Path("missionId") missionId: String?
    ): MissionModel
}