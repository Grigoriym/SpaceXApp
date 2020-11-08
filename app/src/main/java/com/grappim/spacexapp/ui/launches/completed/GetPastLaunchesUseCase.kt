package com.grappim.spacexapp.ui.launches.completed

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetPastLaunchesUseCase @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<LaunchModel>> =
        spaceXRepo.pastLaunches()

}