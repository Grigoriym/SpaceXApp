package com.grappim.spacexapp.ui.info

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.info.InfoModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, InfoModel> =
        spaceXRepository.info()

}