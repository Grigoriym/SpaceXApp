package com.grappim.spacexapp.ui.capsules

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetCapsulesUseCase @Inject constructor(
  private val spaceXRepo: SpaceXRepository
) : UseCase<List<CapsuleModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<CapsuleModel>> =
    spaceXRepo.allCapsules()
}