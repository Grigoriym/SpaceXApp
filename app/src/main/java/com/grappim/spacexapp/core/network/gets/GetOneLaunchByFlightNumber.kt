package com.grappim.spacexapp.core.network.gets

import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.core.functional.Either
import com.grappim.spacexapp.core.functional.Failure
import com.grappim.spacexapp.core.functional.UseCase
import javax.inject.Inject

class GetOneLaunchByFlightNumber @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) : UseCase<LaunchModel, GetOneLaunchByFlightNumber.Params>() {
  override suspend fun run(params: Params): Either<Failure, LaunchModel> =
    spaceXRepository.oneLaunch(params.flightNumber)

  data class Params(
    val flightNumber: Int?
  )
}