package com.grappim.spacexapp.ui.ships

import com.grappim.spacexapp.core.repository.SpaceXRepository
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import javax.inject.Inject

class GetAllShipsUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<ShipModel>> =
        spaceXRepository.allShips()

}