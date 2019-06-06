package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.repository.NewRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetAllCapsules(
  private val newRepo: NewRepository
) : UseCase<List<CapsuleModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<CapsuleModel>> =
    newRepo.allCapsules()
}