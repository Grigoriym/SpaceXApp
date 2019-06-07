package com.grappim.spacexapp.repository

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure

interface NewSpaceXRepository {

  suspend fun allRockets(): Either<Failure, List<RocketModel>>

  suspend fun allCapsules(): Either<Failure, List<CapsuleModel>>

  suspend fun upcomingCapsules(): Either<Failure, List<CapsuleModel>>

  suspend fun pastCapsules(): Either<Failure, List<CapsuleModel>>

  suspend fun allCores(): Either<Failure, List<CoreModel>>

  suspend fun upcomingCores(): Either<Failure, List<CoreModel>>

  suspend fun pastCores(): Either<Failure, List<CoreModel>>

  suspend fun allShips(): Either<Failure, List<ShipModel>>

  suspend fun allLaunchPads(): Either<Failure, List<LaunchPadModel>>

  suspend fun info(): Either<Failure, InfoModel>

  suspend fun history(): Either<Failure, List<HistoryModel>>

  suspend fun allPayloads(): Either<Failure, List<PayloadModel>>

  suspend fun payloadById(payloadId: String?): Either<Failure, PayloadModel>
}