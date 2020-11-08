package com.grappim.spacexapp.ui.capsules

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetPastCapsulesUseCase @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {
    suspend operator fun invoke(): Either<Throwable, List<CapsuleModel>> =
        spaceXRepo.pastCapsules()
}