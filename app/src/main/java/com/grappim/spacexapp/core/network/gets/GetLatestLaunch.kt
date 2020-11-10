package com.grappim.spacexapp.core.network.gets

import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.core.functional.Either
import com.grappim.spacexapp.data.remote.SpaceXRepository
import javax.inject.Inject

class GetLatestLaunch @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, LaunchModel> =
        spaceXRepo.latestLaunch()
}