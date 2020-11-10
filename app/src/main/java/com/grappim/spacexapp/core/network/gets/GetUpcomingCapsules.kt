package com.grappim.spacexapp.core.network.gets

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.capsule.CapsuleModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetUpcomingCapsules @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CapsuleModel>> =
        spaceXRepo.upcomingCapsules()
}