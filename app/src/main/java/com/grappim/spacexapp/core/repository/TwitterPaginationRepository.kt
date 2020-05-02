package com.grappim.spacexapp.core.repository

import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.pagination.Listing

interface TwitterPaginationRepository {

  fun getTweets(screenName: String): Listing<UserTimelineModel>

}