package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase
import javax.inject.Inject

class GetHistory @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) : UseCase<List<HistoryModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<HistoryModel>> =
    spaceXRepository.history()
}