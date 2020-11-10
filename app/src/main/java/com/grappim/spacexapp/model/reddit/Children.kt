package com.grappim.spacexapp.model.reddit


import com.google.gson.annotations.SerializedName

data class Children(
  @SerializedName("data")
  val `data`: RedditModel,
  @SerializedName("kind")
  val kind: String?
)