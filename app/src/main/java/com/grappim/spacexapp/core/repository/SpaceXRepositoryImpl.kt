package com.grappim.spacexapp.core.repository

import com.grappim.spacexapp.api.SpaceXApi
import com.grappim.spacexapp.data.remote.BaseRepository
import com.grappim.spacexapp.di.qualifiers.SpacexApiQualifier
import com.grappim.spacexapp.di.scopes.AppScope
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.NetworkHandler
import com.grappim.spacexapp.network.NetworkHelper
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import javax.inject.Inject

@AppScope
class SpaceXRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    @SpacexApiQualifier private val service: SpaceXApi
) : SpaceXRepository, NetworkHelper, BaseRepository() {

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

    override suspend fun info(): Either<Failure, InfoModel> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getInfo(), InfoModel.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun history(): Either<Throwable, List<HistoryModel>> =
        apiCall { service.getHistory() }

    override suspend fun allPayloads(): Either<Failure, List<PayloadModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getAllPayloads(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun payloadById(payloadId: String?): Either<Failure, PayloadModel> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getPayloadById(payloadId), PayloadModel.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun allLaunches(): Either<Failure, List<LaunchModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getAllLaunches(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun pastLaunches(): Either<Throwable, List<LaunchModel>> =
        apiCall { service.getPastLaunches() }

    override suspend fun upcomingLaunches(): Either<Throwable, List<LaunchModel>> =
        apiCall { service.getUpcomingLaunches() }

    override suspend fun nextLaunch(): Either<Failure, LaunchModel> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getNextLaunch(), LaunchModel.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun latestLaunch(): Either<Failure, LaunchModel> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getLatestLaunch(), LaunchModel.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun oneLaunch(flightNumber: Int?): Either<Failure, LaunchModel> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getOneLaunch(flightNumber), LaunchModel.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}