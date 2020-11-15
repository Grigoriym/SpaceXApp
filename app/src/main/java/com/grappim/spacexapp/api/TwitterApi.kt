package com.grappim.spacexapp.api

import com.grappim.spacexapp.api.model.twitter.TweetModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterApi {

    @GET("statuses/user_timeline.json")
    suspend fun getUserTimelineAsync(
        @Query("user_id") userId: String? = null,
        @Query("screen_name") screenName: String = "SpaceX",
        @Query("count") count: Int = 30,
        @Query("tweet_mode") tweetMode: String = "extended",
        @Query("page") page: Int? = null,
        @Query("include_rts") includeRts: String = "false",
        @Query("exclude_replies") excludeReplies: String = "true",
        @Query("max_id") maxId: Long? = null,
        @Query("since_id") sinceId: Long? = null
    ): List<TweetModel>

}