package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetAllLaunchPads(
  private val spaceXRepo: SpaceXRepository
) : UseCase<List<LaunchPadModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<LaunchPadModel>> =
    spaceXRepo.allLaunchPads()
}