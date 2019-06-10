package com.grappim.spacexapp.network.services

import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.api.TwitterApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit

class TwitterService(retrofit: Retrofit) : TwitterApi {

  private val twitterApi by lazy { retrofit.create(TwitterApi::class.java) }

  override fun getUserTimelineAsync(
    userId: String?,
    screenName: String?,
    count: Int?,
    tweetMode: String?,
    page: Int?,
    includeRts: String?,
    excludeReplies: String?,
    maxId: Long?,
    sinceId: Long?
  ): Deferred<Response<List<UserTimelineModel>>> = TODO()

  override suspend fun newGetUserTimelineAsync(
    userId: String?,
    screenName: String?,
    count: Int?,
    tweetMode: String?,
    page: Int?,
    includeRts: String?,
    excludeReplies: String?,
    maxId: Long?,
    sinceId: Long?
  ): Response<List<UserTimelineModel>> =
    twitterApi.newGetUserTimelineAsync(
      userId,
      screenName,
      count,
      tweetMode,
      page,
      includeRts,
      excludeReplies,
      maxId,
      sinceId
    )

}