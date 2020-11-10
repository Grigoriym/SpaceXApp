package com.grappim.spacexapp.ui.launchpads

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetAllLaunchPadsUseCase @Inject constructor(
  private val spaceXRepo: SpaceXRepository
)  {

  suspend operator fun invoke(): Either<Throwable, List<LaunchPadModel>> =
    spaceXRepo.allLaunchPads()

}