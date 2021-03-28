package com.grappim.spacexapp.data.remote

import com.grappim.spacexapp.api.SpaceXApi
import com.grappim.spacexapp.api.model.capsule.CapsuleModel
import com.grappim.spacexapp.api.model.cores.CoreModel
import com.grappim.spacexapp.api.model.history.HistoryModel
import com.grappim.spacexapp.api.model.info.InfoModel
import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.api.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.api.model.payloads.PayloadModel
import com.grappim.spacexapp.api.model.rocket.RocketModel
import com.grappim.spacexapp.api.model.ships.ShipModel
import com.grappim.spacexapp.core.functional.Either
import com.grappim.spacexapp.di.qualifiers.SpacexApiQualifier
import com.grappim.spacexapp.di.scopes.AppScope
import javax.inject.Inject

interface SpaceXRepository {

    suspend fun allRockets(): Either<Throwable, List<RocketModel>>

    suspend fun allCapsules(): Either<Throwable, List<CapsuleModel>>

    suspend fun upcomingCapsules(): Either<Throwable, List<CapsuleModel>>

    suspend fun pastCapsules(): Either<Throwable, List<CapsuleModel>>

    suspend fun allCores(): Either<Throwable, List<CoreModel>>

    suspend fun upcomingCores(): Either<Throwable, List<CoreModel>>

    suspend fun pastCores(): Either<Throwable, List<CoreModel>>

    suspend fun allShips(): Either<Throwable, List<ShipModel>>

    suspend fun allLaunchPads(): Either<Throwable, List<LaunchPadModel>>

    suspend fun info(): Either<Throwable, InfoModel>

    suspend fun history(): Either<Throwable, List<HistoryModel>>

    suspend fun allPayloads(): Either<Throwable, List<PayloadModel>>

    suspend fun payloadById(payloadId: String): Either<Throwable, PayloadModel>

    suspend fun allLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun pastLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun upcomingLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun nextLaunch(): Either<Throwable, LaunchModel>

    suspend fun latestLaunch(): Either<Throwable, LaunchModel>

    suspend fun oneLaunch(flightNumber: Int?): Either<Throwable, LaunchModel>
}

@AppScope
class SpaceXRepositoryImpl @Inject constructor(
    @SpacexApiQualifier private val service: SpaceXApi
) : SpaceXRepository, BaseRepository() {

    override suspend fun allCapsules(): Either<Throwable, List<CapsuleModel>> =
        apiCall { service.getAllCapsules() }

    override suspend fun upcomingCapsules(): Either<Throwable, List<CapsuleModel>> =
        apiCall { service.getUpcomingCapsules() }

    override suspend fun pastCapsules(): Either<Throwable, List<CapsuleModel>> =
        apiCall { service.getPastCapsules() }

    override suspend fun allRockets(): Either<Throwable, List<RocketModel>> =
        apiCall { service.getAllRockets() }

    override suspend fun allCores(): Either<Throwable, List<CoreModel>> =
        apiCall { service.getAllCores() }

    override suspend fun upcomingCores(): Either<Throwable, List<CoreModel>> =
        apiCall { service.getUpcomingCores() }

    override suspend fun pastCores(): Either<Throwable, List<CoreModel>> =
        apiCall { service.getPastCores() }

    override suspend fun allShips(): Either<Throwable, List<ShipModel>> =
        apiCall { service.getAllShips() }

    override suspend fun allLaunchPads(): Either<Throwable, List<LaunchPadModel>> =
        apiCall { service.getAllLaunchPads() }

    override suspend fun info(): Either<Throwable, InfoModel> =
        apiCall { service.getInfo() }

    override suspend fun history(): Either<Throwable, List<HistoryModel>> =
        apiCall { service.getHistory() }

    override suspend fun allPayloads(): Either<Throwable, List<PayloadModel>> =
        apiCall { service.getAllPayloads() }

    override suspend fun payloadById(payloadId: String): Either<Throwable, PayloadModel> =
        apiCall { service.getPayloadById(payloadId) }

    override suspend fun allLaunches(): Either<Throwable, List<LaunchModel>> =
        apiCall { service.getAllLaunches() }

    override suspend fun pastLaunches(): Either<Throwable, List<LaunchModel>> =
        apiCall { service.getPastLaunches() }

    override suspend fun upcomingLaunches(): Either<Throwable, List<LaunchModel>> =
        apiCall { service.getUpcomingLaunches() }

    override suspend fun nextLaunch(): Either<Throwable, LaunchModel> =
        apiCall { service.getNextLaunch() }

    override suspend fun latestLaunch(): Either<Throwable, LaunchModel> =
        apiCall { service.getLatestLaunch() }

    override suspend fun oneLaunch(flightNumber: Int?): Either<Throwable, LaunchModel> =
        apiCall { service.getOneLaunch(flightNumber) }
}