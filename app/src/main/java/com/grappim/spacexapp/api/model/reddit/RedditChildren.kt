package com.grappim.spacexapp.api.model.reddit


import com.google.gson.annotations.SerializedName

data class RedditChildren(
  @SerializedName("data")
  val `data`: RedditModel,
  @SerializedName("kind")
  val kind: String?
)