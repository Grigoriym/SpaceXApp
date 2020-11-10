package com.grappim.spacexapp.api.model.twitter


import com.google.gson.annotations.SerializedName

data class Entities(
  @SerializedName("hashtags")
  val hashtags: List<Any?>?,
  @SerializedName("symbols")
  val symbols: List<Any?>?,
  @SerializedName("urls")
  val urls: List<Any?>?,
  @SerializedName("user_mentions")
  val userMentions: List<Any?>?
)