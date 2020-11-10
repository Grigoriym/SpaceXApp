package com.grappim.spacexapp.ui.missions_payloads

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetPayloadByIdUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(payloadId: String): Either<Throwable, PayloadModel> =
        spaceXRepository.payloadById(payloadId)

}