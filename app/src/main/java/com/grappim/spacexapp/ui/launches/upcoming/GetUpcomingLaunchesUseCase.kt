package com.grappim.spacexapp.ui.launches.upcoming

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetUpcomingLaunchesUseCase @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<LaunchModel>> =
        spaceXRepo.upcomingLaunches()

}