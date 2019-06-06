package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpacexApi {

  @GET("rockets")
  suspend fun getAllRockets(): Response<List<RocketModel>>

  @GET("capsules")
  suspend fun getAllCapsules(): Response<List<CapsuleModel>>

  @GET("capsules/upcoming")
  suspend fun getUpcomingCapsules(): Response<List<CapsuleModel>>

  @GET("capsules/past")
  suspend fun getPastCapsules(): Response<List<CapsuleModel>>

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


}