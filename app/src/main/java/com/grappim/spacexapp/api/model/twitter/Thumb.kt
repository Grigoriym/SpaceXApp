package com.grappim.spacexapp.api.model.twitter

import com.google.gson.annotations.SerializedName

data class Thumb(
  @SerializedName("h")
  val h: Int?,
  @SerializedName("resize")
  val resize: String?,
  @SerializedName("w")
  val w: Int?
)