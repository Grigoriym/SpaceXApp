package com.grappim.spacexapp.api

import com.grappim.spacexapp.api.model.reddit.RedditListingResponse
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
  ): RedditListingResponse

}