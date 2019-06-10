package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.repository.TwitterRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetUserTimeline(
  private val twitterRepo: TwitterRepository
) : UseCase<List<UserTimelineModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<UserTimelineModel>> =
    twitterRepo.userTimeline()
}