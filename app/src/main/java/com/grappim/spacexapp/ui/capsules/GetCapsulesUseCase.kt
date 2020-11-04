package com.grappim.spacexapp.ui.capsules

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetCapsulesUseCase @Inject constructor(
    private val spaceXRepo: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<CapsuleModel>> =
        spaceXRepo.allCapsules()

}