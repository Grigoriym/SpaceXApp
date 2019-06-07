package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.services.TwitterService
import com.grappim.spacexapp.repository.TwitterRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure

class TwitterNetwork(
  private val networkHandler: NetworkHandler,
  private val service: TwitterService
) : TwitterRepository, NetworkHelper() {

  override suspend fun userTimeline(): Either<Failure, List<UserTimelineModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.newGetUserTimelineAsync(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

}