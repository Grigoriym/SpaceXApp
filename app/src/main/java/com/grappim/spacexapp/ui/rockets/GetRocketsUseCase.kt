package com.grappim.spacexapp.ui.rockets

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.rocket.RocketModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetRocketsUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<RocketModel>> =
        spaceXRepository.allRockets()

}