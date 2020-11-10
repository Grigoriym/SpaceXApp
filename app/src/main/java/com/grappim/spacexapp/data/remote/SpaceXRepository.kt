package com.grappim.spacexapp.data.remote

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
import com.grappim.spacexapp.core.functional.Failure

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

    suspend fun allLaunches(): Either<Failure, List<LaunchModel>>

    suspend fun pastLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun upcomingLaunches(): Either<Throwable, List<LaunchModel>>

    suspend fun nextLaunch(): Either<Failure, LaunchModel>

    suspend fun latestLaunch(): Either<Failure, LaunchModel>

    suspend fun oneLaunch(flightNumber: Int?): Either<Failure, LaunchModel>
}