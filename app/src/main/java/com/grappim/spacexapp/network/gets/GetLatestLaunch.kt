package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetLatestLaunch @Inject constructor(
  private val spaceXRepo: SpaceXRepository
) : UseCase<LaunchModel, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, LaunchModel> =
    spaceXRepo.latestLaunch()
}