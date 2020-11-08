package com.grappim.spacexapp.ui.rockets

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetRocketsUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<RocketModel>> =
        spaceXRepository.allRockets()

}