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
        apiCall { service.getAllCapsules()}

    override suspend fun upcomingCapsules(): Either<Throwable, List<CapsuleModel>> =
        apiCall { service.getUpcomingCapsules() }

    override suspend fun pastCapsules(): Either<Throwable, List<CapsuleModel>> =
        apiCall { service.getPastCapsules() }

    override suspend fun allRockets(): Either<Failure, List<RocketModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getAllRockets(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun allCores(): Either<Failure, List<CoreModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getAllCores(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun upcomingCores(): Either<Failure, List<CoreModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getUpcomingCores(), emptyList())
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun pastCores(): Either<Failure, List<CoreModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getPastCores(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun allShips(): Either<Failure, List<ShipModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getAllShips(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun allLaunchPads(): Either<Failure, List<LaunchPadModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getAllLaunchPads(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun info(): Either<Failure, InfoModel> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getInfo(), InfoModel.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun history(): Either<Failure, List<HistoryModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getHistory(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

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

    override suspend fun pastLaunches(): Either<Failure, List<LaunchModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getPastLaunches(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun upcomingLaunches(): Either<Failure, List<LaunchModel>> {
        return when (networkHandler.isConnected) {
            true -> makeRequest(service.getUpcomingLaunches(), emptyList())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

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