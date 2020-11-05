package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetUpcomingCores @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CoreModel>> =
        spaceXRepository.upcomingCores()
}