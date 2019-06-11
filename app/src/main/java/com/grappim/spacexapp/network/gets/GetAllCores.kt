package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetAllCores(
  private val spaceXRepository: SpaceXRepository
) : UseCase<List<CoreModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<CoreModel>> =
    spaceXRepository.allCores()
}