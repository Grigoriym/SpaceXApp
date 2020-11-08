package com.grappim.spacexapp.ui.history

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.util.Either
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<HistoryModel>> =
        spaceXRepository.history()

}