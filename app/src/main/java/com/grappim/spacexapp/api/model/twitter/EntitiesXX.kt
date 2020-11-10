package com.grappim.spacexapp.api.model.twitter


import com.google.gson.annotations.SerializedName

data class EntitiesXX(
  @SerializedName("hashtags")
  val hashtags: List<Any?>?,
  @SerializedName("media")
  val media: List<MediaXX?>?,
  @SerializedName("symbols")
  val symbols: List<Any?>?,
  @SerializedName("urls")
  val urls: List<UrlXX?>?,
  @SerializedName("user_mentions")
  val userMentions: List<UserMention?>?
)