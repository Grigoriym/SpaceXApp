package com.grappim.spacexapp.ui.cores

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.cores.CoreModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetUpcomingCoresUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CoreModel>> =
        spaceXRepository.upcomingCores()
}