package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetAllShips(
  private val spaceXRepository: SpaceXRepository
) : UseCase<List<ShipModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<ShipModel>> =
    spaceXRepository.allShips()
}