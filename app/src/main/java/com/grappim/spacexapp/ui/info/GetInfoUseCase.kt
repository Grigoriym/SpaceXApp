package com.grappim.spacexapp.ui.info

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, InfoModel> =
        spaceXRepository.info()

}