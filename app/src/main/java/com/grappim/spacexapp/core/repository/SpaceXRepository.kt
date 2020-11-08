package com.grappim.spacexapp.core.repository

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure

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

    suspend fun info(): Either<Failure, InfoModel>

    suspend fun history(): Either<Throwable, List<HistoryModel>>

    suspend fun allPayloads(): Either<Failure, List<PayloadModel>>

    suspend fun payloadById(payloadId: String?): Either<Failure, PayloadModel>

    suspend fun allLaunches(): Either<Failure, List<LaunchModel>>

    suspend fun pastLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun upcomingLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun nextLaunch(): Either<Failure, LaunchModel>

    suspend fun latestLaunch(): Either<Failure, LaunchModel>

    suspend fun oneLaunch(flightNumber: Int?): Either<Failure, LaunchModel>
}