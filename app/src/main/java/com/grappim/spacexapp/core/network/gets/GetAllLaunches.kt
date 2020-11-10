package com.grappim.spacexapp.core.network.gets

import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.core.functional.Either
import com.grappim.spacexapp.core.functional.Failure
import com.grappim.spacexapp.core.functional.UseCase
import javax.inject.Inject

class GetAllLaunches @Inject constructor(
  private val spaceXRepo: SpaceXRepository
) : UseCase<List<LaunchModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<LaunchModel>> =
    spaceXRepo.allLaunches()
}