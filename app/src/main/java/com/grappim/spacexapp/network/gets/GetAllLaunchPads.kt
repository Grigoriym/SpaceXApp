package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.repository.NewSpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetAllLaunchPads(
  private val newSpaceXRepo: NewSpaceXRepository
) : UseCase<List<LaunchPadModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<LaunchPadModel>> =
    newSpaceXRepo.allLaunchPads()
}