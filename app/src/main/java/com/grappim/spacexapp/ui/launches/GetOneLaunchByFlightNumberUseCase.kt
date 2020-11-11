package com.grappim.spacexapp.ui.launches

import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.core.functional.Either
import com.grappim.spacexapp.data.remote.SpaceXRepository
import javax.inject.Inject

class GetOneLaunchByFlightNumberUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend operator fun invoke(flightNumber: Int?): Either<Throwable, LaunchModel> =
        spaceXRepository.oneLaunch(flightNumber)

}