package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetInfo(
  private val spaceXRepository: SpaceXRepository
) : UseCase<InfoModel, UseCase.None>() {
  override suspend fun run(params: UseCase.None): Either<Failure, InfoModel> =
    spaceXRepository.info()
}