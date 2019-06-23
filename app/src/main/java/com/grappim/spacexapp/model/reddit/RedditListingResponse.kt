package com.grappim.spacexapp.model.reddit


import com.google.gson.annotations.SerializedName

data class RedditListingResponse(
  @SerializedName("data")
  val `data`: Data?,
  @SerializedName("kind")
  val kind: String?
)