package com.grappim.spacexapp.core.network.gets

import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetAllLaunches @Inject constructor(
  private val spaceXRepo: SpaceXRepository
)  {
  suspend operator fun invoke(): Either<Throwable, List<LaunchModel>> =
    spaceXRepo.allLaunches()
}