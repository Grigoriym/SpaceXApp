package com.grappim.spacexapp.network.services

import com.grappim.spacexapp.model.reddit.RedditListingResponse
import com.grappim.spacexapp.network.api.RedditApi
import retrofit2.Response
import retrofit2.Retrofit

class RedditService(
  retrofit: Retrofit
) : RedditApi {

  private val redditApi by lazy { retrofit.create(RedditApi::class.java) }

  override suspend fun getPostsBySubreddit(
    subreddit: String,
    after: String?,
    before: String?,
    limit: Int?
  ): Response<RedditListingResponse> =
    redditApi.getPostsBySubreddit(
      subreddit, after, before, limit
    )

}