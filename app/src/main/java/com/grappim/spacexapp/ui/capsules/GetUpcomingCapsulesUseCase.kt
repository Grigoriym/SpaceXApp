package com.grappim.spacexapp.ui.capsules

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.capsule.CapsuleModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetUpcomingCapsulesUseCase @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CapsuleModel>> =
        spaceXRepo.upcomingCapsules()
}