package com.grappim.spacexapp.ui.cores

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetCoresUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<CoreModel>> =
        spaceXRepository.allCores()

}