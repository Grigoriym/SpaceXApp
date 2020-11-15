package com.grappim.spacexapp.api.model.twitter

import com.google.gson.annotations.SerializedName

data class Sizes(
  @SerializedName("large")
  val large: Large?,
  @SerializedName("medium")
  val medium: Medium?,
  @SerializedName("small")
  val small: Small?,
  @SerializedName("thumb")
  val thumb: Thumb?
)