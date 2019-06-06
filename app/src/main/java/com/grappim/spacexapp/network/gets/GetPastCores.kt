package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.repository.NewRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetPastCores(
  private val newRepository: NewRepository
) : UseCase<List<CoreModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<CoreModel>> =
    newRepository.pastCores()
}