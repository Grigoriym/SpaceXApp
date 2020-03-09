package com.grappim.spacexapp.network.services

import com.grappim.spacexapp.model.reddit.RedditListingResponse
import com.grappim.spacexapp.network.api.RedditApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class RedditService @Inject constructor(
//  val redditApi: RedditApi
//) : RedditApi {
//
//  override suspend fun getPostsBySubreddit(
//    subreddit: String?,
//    after: String?,
//    before: String?,
//    limit: Int?
//  ): Response<RedditListingResponse> =
//    redditApi.getPostsBySubreddit(
//      subreddit,
//      after,
//      before,
//      limit
//    )
//
//}