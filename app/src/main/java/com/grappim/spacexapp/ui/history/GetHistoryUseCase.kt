package com.grappim.spacexapp.ui.history

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.history.HistoryModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
  private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<HistoryModel>> =
        spaceXRepository.history()

}