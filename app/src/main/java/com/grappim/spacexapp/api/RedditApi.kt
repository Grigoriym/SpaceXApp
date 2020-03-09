package com.grappim.spacexapp.api

import com.grappim.spacexapp.model.reddit.RedditListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApi {

  @GET("r/{subreddit}/hot.json")
  suspend fun getPostsBySubreddit(
    @Path("subreddit") subreddit: String? = "spacex",
    @Query("after") after: String? = null,
    @Query("before") before: String? = null,
    @Query("limit") limit: Int? = null
  ): Response<RedditListingResponse>

  @GET("r/{subreddit}/hot.json")
  suspend fun getPostsBySubredditSingle(
    @Path("subreddit") subreddit: String? = "spacex",
    @Query("after") after: String? = null,
    @Query("before") before: String? = null,
    @Query("limit") limit: Int? = null
  ): Single<RedditListingResponse>

}