package com.grappim.spacexapp.ui.missions_payloads

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.payloads.PayloadModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetAllPayloadsUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<PayloadModel>> =
        spaceXRepository.allPayloads()

}