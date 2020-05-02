package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetUpcomingCapsules @Inject constructor(
  private val spaceXRepo: SpaceXRepository
) : UseCase<List<CapsuleModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<CapsuleModel>> =
    spaceXRepo.upcomingCapsules()
}