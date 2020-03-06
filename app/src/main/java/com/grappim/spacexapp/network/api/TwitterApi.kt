package com.grappim.spacexapp.network.api

import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.core.utils.TWITTER_USER_TIMELINE_GET
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterApi {

  @GET(TWITTER_USER_TIMELINE_GET)
  suspend fun getUserTimelineAsync(
    @Query("user_id") userId: String? = null,
    @Query("screen_name") screenName: String? = "SpaceX",
    @Query("count") count: Int? = 30,
    @Query("tweet_mode") tweetMode: String? = "extended",
    @Query("page") page: Int? = null,
    @Query("include_rts") includeRts: String? = "false",
    @Query("exclude_replies") excludeReplies: String? = "true",
    @Query("max_id") maxId: Long? = null,
    @Query("since_id") sinceId: Long? = null
  ): Response<List<UserTimelineModel>>
}