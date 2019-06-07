package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.repository.NewSpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetPayloadById(
  private val newSpaceXRepository: NewSpaceXRepository
) : UseCase<PayloadModel, GetPayloadById.Params>() {
  override suspend fun run(params: Params): Either<Failure, PayloadModel> =
    newSpaceXRepository.payloadById(params.payloadId)

  data class Params(
    val payloadId: String?
  )
}