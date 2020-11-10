package com.grappim.spacexapp.ui.ships

import com.grappim.spacexapp.data.remote.SpaceXRepository
import com.grappim.spacexapp.api.model.ships.ShipModel
import com.grappim.spacexapp.core.functional.Either
import javax.inject.Inject

class GetAllShipsUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<ShipModel>> =
        spaceXRepository.allShips()

}