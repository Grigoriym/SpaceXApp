package com.grappim.spacexapp.ui.cores

import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetCoresUseCase @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) : UseCase<List<CoreModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<CoreModel>> =
    spaceXRepository.allCores()
}