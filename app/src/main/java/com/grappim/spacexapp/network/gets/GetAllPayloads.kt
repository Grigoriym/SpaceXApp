package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetAllPayloads @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) : UseCase<List<PayloadModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<PayloadModel>> =
    spaceXRepository.allPayloads()
}