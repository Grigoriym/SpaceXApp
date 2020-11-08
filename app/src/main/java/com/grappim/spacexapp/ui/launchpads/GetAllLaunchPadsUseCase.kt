package com.grappim.spacexapp.ui.launchpads

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetAllLaunchPadsUseCase @Inject constructor(
  private val spaceXRepo: SpaceXRepository
)  {

  suspend operator fun invoke(): Either<Throwable, List<LaunchPadModel>> =
    spaceXRepo.allLaunchPads()

}