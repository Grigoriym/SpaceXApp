package com.grappim.spacexapp.repository

import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure

interface TwitterRepository {

  suspend fun userTimeline(): Either<Failure, List<UserTimelineModel>>

}