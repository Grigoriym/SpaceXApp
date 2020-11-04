package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetPastCapsules @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CapsuleModel>> =
        spaceXRepo.pastCapsules()
}